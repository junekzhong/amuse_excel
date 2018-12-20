package com.zys.amuse.web.controller;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.SheetInfo;
import com.zys.amuse.jdbc.helper.PageSearcher;
import com.zys.amuse.jdbc.service.ISheetService;
import com.zys.amuse.web.util.ResultUtils;
import com.zys.amuse.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhongjunkai on 18/12/3.
 */
@RestController
@RequestMapping("sheet")
public class SheetController {

    @Autowired
    private ISheetService sheetService;

    @PostMapping("sheet")
    public Result createSheet(@RequestBody SheetInfo sheetInfo) {
        Integer id = sheetService.add(sheetInfo);
        return ResultUtils.success(id);
    }

    @PutMapping("sheet")
    public Result updateSheet(@RequestBody SheetInfo sheetInfo) {
        sheetService.update(sheetInfo);
        return ResultUtils.success();
    }

    @DeleteMapping("sheet/{sheetId}")
    public Result delete(@PathVariable("sheetId") Integer sheetId) {
        sheetService.delete(sheetId);
        return ResultUtils.success();
    }

    @DeleteMapping("sheets/{bookId}")
    public Result deleteByBookId(@PathVariable("bookId") Integer bookId) {
        sheetService.deleteByBookId(bookId);
        return ResultUtils.success();
    }

    @GetMapping("sheet/{sheetId}")
    public Result<SheetInfo> showDetail(@PathVariable Integer sheetId) {
        SheetInfo sheetInfo = sheetService.showDetail(sheetId);
        return ResultUtils.success(sheetInfo);
    }

    @GetMapping("sheets")
    public Result<PageInfo<SheetInfo>> listSheet(PageSearcher searcher, @RequestParam(required = false) Integer bookId) {
        PageInfo<SheetInfo> data;
        if(null == bookId) {
            data = sheetService.listSheet(searcher.getPageNum(), searcher.getPageSize());
        } else {
            data = sheetService.listSheet(searcher.getPageNum(), searcher.getPageSize(), bookId);
        }
        return ResultUtils.success(data);
    }
}
