package com.zys.amuse.excel;

import com.zys.amuse.basis.ColumnInfo;
import com.zys.amuse.basis.DataHandler;
import com.zys.amuse.excel.utils.CellUtils;
import com.zys.amuse.excel.valid.ValidState;
import com.zys.amuse.excel.valid.Validator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * excel内容处理器，实现了{@link Importer}接口和{@link Exporter}接口，
 * 通过模板方法定义了导入数据和导出数据的流程
 *
 * Created by zhongjunkai on 18/11/16.
 */
public abstract class AbstractExcelProcessor implements Importer, Exporter {

    private static Logger logger = LoggerFactory.getLogger(AbstractExcelProcessor.class);

    private DataHandler dataHandler;

    public AbstractExcelProcessor(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    @Override
    public void importResource(InputStream inputStream, OutputStream outputStream, String dataTable, List<ColumnInfo> headers) {
        List<Map<String, Object>> errors = new LinkedList<>();
        try {
            doImport(outputStream, headers, errors, dataTable, inputStream);
        } finally {
            //调用导出excel的方法，将错误数据进行导出
            if(!errors.isEmpty()) {
                this.exportResource(outputStream, headers, errors);
            }
        }
    }

    /**
     * 导入数据，并通过输出流返回异常数据
     *
     * @param file
     * @param outputStream
     */
    @Override
    public void importResource(File file, OutputStream outputStream, List<ColumnInfo> headCols) {
        List<Map<String, Object>> errors = new LinkedList<>();
        //根据文件名获取数据导入的表名称
        String tableName = file.getName();
        //获取表头对应的字段
        try (InputStream in = new FileInputStream(file)) {
            doImport(outputStream, headCols, errors, tableName, in);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            //调用导出excel的方法，将错误数据进行导出
            if(!errors.isEmpty()) {
                this.exportResource(outputStream, headCols, errors);
            }
        }
    }

    private void doImport(OutputStream outputStream, List<ColumnInfo> headCols, List<Map<String, Object>> errors, String tableName, InputStream in) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(in);
            //获取第一个sheet，目前只支持一个sheet的导入
            Sheet sheet = workbook.getSheetAt(0);
            //获取第一行
            Row headRow = sheet.getRow(0);
            //最后一个单元格编号
            int lastCellNum = headRow.getLastCellNum();
            //第一个单元格编号
            int firstCellNum = headRow.getFirstCellNum();
            //批处理Id
            int batchId = 0;
            //数据集合
            List<Map<String, Object>> datas = new LinkedList<>();
            //错误数据处理策略，如果没有设置异常
            String errorPolicy = "".equals(errorPolicy()) ? ErrorPolicy.FLUENCY.getValue() : errorPolicy();
            //获取单元格的所有验证器
            Map<String, List<Validator>> validators = processValidatorMap(headCols, tableName);
            //遍历数据
            for(Row row : sheet) {
                batchId++;
                if(row.getRowNum() == 0) continue;
                //行数据的封装结果
                Map<String, Object> rowData = new HashMap<>();
                //单元格验证器验证结果
                boolean valid = true;
                //遍历获取每行所有单元格的值，并封装到map中
                for(int cn = firstCellNum; cn < lastCellNum; cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //获取单元格的验证器
                    ColumnInfo col = headCols.get(cn);
                    String colName = col.getColName();
                    valid = doValidate(rowData, valid, cell, colName, validators);
                    //获取单元格的值
                    Object cellValue = CellUtils.getCellValue(cell);
                    rowData.put(colName, cellValue);
                }
                //根据单元格验证结果，如果某一行中有某个单元格验证不通过，则将该行数据归为错误数据集合中(可用于返回错误数据)
                if(valid) {
                    datas.add(rowData);
                } else {
                    if(errorPolicy.equals(ErrorPolicy.FLUENCY.getValue())) {
                        errors.add(rowData);
                    } else if(errorPolicy.equals(ErrorPolicy.INTERRUPT.getValue())) {
                        errors.add(rowData);
                        //将错误信息导出，并终止循环
                        this.exportResource(outputStream, headCols, errors);
                        //清空错误列表，防止在finally中再执行
                        errors.clear();
                        break;
                    }
                }
                //如果是流畅策略，则将所有正常数据都导入，统一返回异常数据
                if(errorPolicy.equals(ErrorPolicy.FLUENCY.getValue())) {
                    //100条数据处理一次或者到达最后一行时处理
                    if(batchId % 100 == 0 || batchId == (sheet.getLastRowNum() + 1)) {
                        //将数据保存到数据库中
                        dataHandler.persistent(tableName, datas);
                        //将数据集合进行重新初始化
                        datas.clear();
                    }
                } else if(errorPolicy.equals(ErrorPolicy.INTERRUPT.getValue())) {
                    //如果是中断策略，由于需要中断，不能使用批量导入
                    dataHandler.persistent(tableName, datas);
                    //将数据集合进行重新初始化
                    datas.clear();
                }
            }
        } catch (IOException | InvalidFormatException e) {
            logger.error(e.getMessage());
        } finally {
            if(workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 验证单元格格式
     *
     * @param rowData 行数据map
     * @param valid 当前行所有单元格的验证结果
     * @param cell 当前验证的单元格
     * @param colName 当前单元格对应的字段名称
     * @param validators 当前单元格的验证器
     * @return
     */
    private boolean doValidate(Map<String, Object> rowData, boolean valid, Cell cell, String colName, Map<String, List<Validator>> validators) {
        //获取字段定义的验证器
        List<Validator> cellValidators = validators.get(colName);
        if(null != cellValidators) {
            String errorMsg;
            //遍历验证器验证单元格的值
            for (Validator validator : cellValidators) {
                ValidState validState = validator.isValidWithError(cell);
                valid = valid && validState.isValid();
                errorMsg = validState.getMsg();
                //如果单元格验证不通过，为该单元格设置未通过的原因备注
                if (!validState.isValid()) {
                    //将单元格的错误信息也放到结果中后缀_error
                    rowData.put(colName + "_error", errorMsg);
                }
            }
        }
        return valid;
    }

    /**
     * 将所有单元格的验证器存到map集合中，以字段名称为key，验证器集合为value
     *
     * @param headCols 字段列表
     * @param tableName 表名
     * @return
     */
    private Map<String, List<Validator>> processValidatorMap(List<ColumnInfo> headCols, String tableName) {
        Map<String, List<Validator>> validators = new HashMap<>();
        for(ColumnInfo columnInfo : headCols) {
            List<Validator> cellValidators = getCellValidators(tableName, columnInfo);
            //如果有为该字段定义验证器
            if(null != cellValidators && !cellValidators.isEmpty()) {
                validators.put(columnInfo.getColName(), cellValidators);
            }
        }
        return validators;
    }

    /**
     * 将数据导出为excel文件
     *
     * @param out
     * @param data
     */
    @Override
    public void exportResource(OutputStream out, List<ColumnInfo> headers, List<Map<String, Object>> data) {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Drawing drawing = sheet.createDrawingPatriarch();
            CreationHelper factory = workbook.getCreationHelper();
            //创建表头行
            Row headRow = sheet.createRow(0);
            for(int i = 0; i < headers.size(); i++) {
                Cell cell = headRow.createCell(i);
                cell.setCellValue(headers.get(i).getColDesc());
            }
            //设置导出的内容
            if(null != data && !data.isEmpty()) {
                for(int i = 0; i < data.size(); i++) {
                    Map<String, Object> dataInfo = data.get(i);
                    Row row = sheet.createRow(i + 1);
                    int z = 0;
                    for(ColumnInfo col : headers) {
                        //获取字段对应的值
                        Object value = dataInfo.get(col.getColName());
                        String type = col.getColType();
                        Cell cell = row.createCell(z++);
                        //如果该单元格有异常信息，则将该异常信息已注释的形式添加到该单元格上，主要用于处理异常数据
                        Object errorMsg = dataInfo.get(col.getColName() + "_error");
                        if(null != errorMsg) {
                            //设置异常注释信息
                            ClientAnchor anchor = factory.createClientAnchor();
                            //注释宽度
                            anchor.setCol1(cell.getColumnIndex());
                            anchor.setCol2(cell.getColumnIndex()+4);
                            //注释高度
                            anchor.setRow1(row.getRowNum());
                            anchor.setRow2(row.getRowNum()+3);
                            Comment comment = drawing.createCellComment(anchor);
                            //注释内容
                            RichTextString error = factory.createRichTextString((String)errorMsg);
                            comment.setString(error);
                            //将注释添加到单元格上
                            cell.setCellComment(comment);
                        }
                        CellUtils.SetCellValue(cell, value, type, workbook);
                    }
                }
            }
            workbook.write(out);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 获取该单元格的验证器
     *
     * @param col 对应的字段
     * @return
     */
    protected abstract List<Validator> getCellValidators(String tableName, ColumnInfo col);

    /**
     * 错误处理策略，具体的值在{@link ErrorPolicy}枚举类中
     * @return
     */
    protected abstract String errorPolicy() ;

}
