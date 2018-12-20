package com.zys.amuse.web.controller;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.excel.CellValueType;
import com.zys.amuse.jdbc.entity.ColumnInfo;
import com.zys.amuse.jdbc.service.IColumnService;
import com.zys.amuse.jdbc.service.ISheetService;
import com.zys.amuse.web.config.ExcelProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhongjunkai on 18/12/10.
 */
@RestController
public class TestController {

    @Autowired
    private ExcelProcessor excelProcessor;

    @Autowired
    private IColumnService columnService;

    @Autowired
    private ISheetService sheetService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("testExport")
    public void exportData(HttpServletResponse response, Integer sheetId) {
        PageInfo<ColumnInfo> columnInfoPageInfo = columnService.listColumn(1, 1000, sheetId);
        List<ColumnInfo> data = columnInfoPageInfo.getList();
        List<com.zys.amuse.basis.ColumnInfo> headers = new ArrayList<>();
        for(ColumnInfo col : data) {
            com.zys.amuse.basis.ColumnInfo c = new com.zys.amuse.basis.ColumnInfo();
            c.setColName(col.getColName());
            c.setColDesc(col.getColComment());
            c.setColType(col.getColType());
            headers.add(c);
        }
        try {
            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename="
                    + URLEncoder.encode("测试数据.xls", "utf-8"));
            List<Map<String, Object>> outs = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                Map<String, Object> d = new HashMap<>();
                d.put("id", i);
                d.put("name", "姓名" + i);
                d.put("age", 10 + i);
                outs.add(d);
            }
            excelProcessor.exportResource(out, headers, outs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        List<com.zys.amuse.basis.ColumnInfo> headCols = getHeadCols();
        try {
            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename="
                    + URLEncoder.encode("data.xls", "utf-8"));
            String sql = "select code, name, create_time, sort_num from test";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            excelProcessor.exportResource(out, headCols, maps);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("testImport")
    public void importData(HttpServletResponse response, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            excelProcessor.importResource(inputStream, response.getOutputStream(), "test", getHeadCols());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<com.zys.amuse.basis.ColumnInfo> getHeadCols() {
        com.zys.amuse.basis.ColumnInfo code = new com.zys.amuse.basis.ColumnInfo();
        code.setColName("code");
        code.setColDesc("代码");
        code.setColSize(50);
        code.setColType(CellValueType.VARCHAR);
        com.zys.amuse.basis.ColumnInfo name = new com.zys.amuse.basis.ColumnInfo();
        name.setColName("name");
        name.setColDesc("代码");
        name.setColSize(80);
        name.setColType(CellValueType.VARCHAR);
        com.zys.amuse.basis.ColumnInfo ct = new com.zys.amuse.basis.ColumnInfo();
        ct.setColName("create_time");
        ct.setColDesc("创建时间");
        ct.setColType(CellValueType.DATETIME);
        com.zys.amuse.basis.ColumnInfo sn = new com.zys.amuse.basis.ColumnInfo();
        sn.setColName("sort_num");
        sn.setColDesc("排序号");
        sn.setColType(CellValueType.FLOAT);
        sn.setColSize(2);
        return Arrays.asList(code, name, ct, sn);
    }
}
