package com.zys.amuse.web.controller;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.ColumnInfo;
import com.zys.amuse.jdbc.helper.PageSearcher;
import com.zys.amuse.jdbc.service.IColumnService;
import com.zys.amuse.web.util.ResultUtils;
import com.zys.amuse.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhongjunkai on 18/12/3.
 */
@RestController
@RequestMapping("column")
public class ColumnController {

    @Autowired
    private IColumnService columnService;

    @PostMapping("column")
    public Result createColumn(@RequestBody ColumnInfo columnInfo) {
        columnService.add(columnInfo);
        return ResultUtils.success();
    }

    @PutMapping("column")
    public Result updateColumn(@RequestBody ColumnInfo columnInfo) {
        columnService.update(columnInfo);
        return ResultUtils.success();
    }

    @DeleteMapping("column/{colId}")
    public Result delete(@PathVariable("colId") Integer colId) {
        columnService.delete(colId);
        return ResultUtils.success();
    }

    @DeleteMapping("columns/{sheetId}")
    public Result deleteBySheetId(@PathVariable("sheetId")Integer sheetId) {
        columnService.deleteBySheetId(sheetId);
        return ResultUtils.success();
    }

    @GetMapping("column/{colId}")
    public Result<ColumnInfo> showDetail(@PathVariable("colId")Integer colId) {
        ColumnInfo data = columnService.showDetail(colId);
        return ResultUtils.success(data);
    }

    @GetMapping("columns")
    public Result<PageInfo<ColumnInfo>> listColumns(PageSearcher searcher, @RequestParam(required = false) Integer sheetId) {
        PageInfo<ColumnInfo> data;
        if(null == sheetId) {
            data = columnService.listColumn(searcher.getPageNum(), searcher.getPageSize());
        } else {
            data = columnService.listColumn(searcher.getPageNum(), searcher.getPageSize(), sheetId);
        }
        return ResultUtils.success(data);
    }
}
