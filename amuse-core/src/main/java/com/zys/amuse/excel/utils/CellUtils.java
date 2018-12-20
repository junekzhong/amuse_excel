package com.zys.amuse.excel.utils;

import com.zys.amuse.excel.CellValueType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Date;

/**
 * 单元格工具类
 * <p>
 * Created by zhongjunkai on 18/11/20.
 */
public class CellUtils {


    /**
     * 获取单元格的值
     *
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object cellValue = null;
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                break;
            case STRING:
                String valueString = cell.getRichStringCellValue().getString();
                try {
                    cellValue = JodaTimeUtils.parseStandDate(valueString);
                } catch (Exception e) {
                    try {
                        cellValue = JodaTimeUtils.parseStandTime(valueString);
                    } catch (Exception ex) {
                        cellValue = valueString;
                    }
                }
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    cellValue = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     * 设置单元格的值，按照字段的数据类型调整单元格的值类型
     *
     * @param cell
     * @param value
     * @param cellType
     */
    public static void SetCellValue(Cell cell, Object value, String cellType, Workbook workbook) {
        switch (cellType) {
            case CellValueType.DATE:
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy-MM-dd"));
                cell.setCellStyle(cellStyle);
                cell.setCellValue((Date) value);
                break;
            case CellValueType.VARCHAR:
                cell.setCellValue((String) value);
                break;
            case CellValueType.CHAR:
                cell.setCellValue((String) value);
                break;
            case CellValueType.INTEGER:
                if (value instanceof Double) {
                    cell.setCellValue(((Double) value).intValue());
                } else if (value instanceof String) {
                    //如果字符串非空，则将该字符串转为整型数字
                    if(!"".equals(value)) {
                        cell.setCellValue(Integer.parseInt((String) value));
                    } else {
                        cell.setCellValue("");
                    }
                } else {
                    cell.setCellValue((int) value);
                }
                break;
            case CellValueType.FLOAT:
                cell.setCellValue((Float) value);
                break;
            case CellValueType.DOUBLE:
                cell.setCellValue((Double) value);
                break;
            case CellValueType.DATETIME:
                cellStyle = workbook.createCellStyle();
                cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
                cell.setCellStyle(cellStyle);
                cell.setCellValue((Date) value);
                break;
            default:
                cell.setCellValue((String) value);
        }
    }
}
