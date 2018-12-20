package com.zys.amuse.web.controller;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.BookInfo;
import com.zys.amuse.jdbc.helper.PageSearcher;
import com.zys.amuse.jdbc.service.IBookService;
import com.zys.amuse.web.util.ResultUtils;
import com.zys.amuse.web.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhongjunkai on 18/12/3.
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("book")
    public Result createBook(@RequestBody BookInfo bookInfo) {
        bookService.add(bookInfo);
        return ResultUtils.success();
    }

    @PutMapping("book")
    public Result updateBook(@RequestBody BookInfo bookInfo) {
        bookService.update(bookInfo);
        return ResultUtils.success();
    }

    @DeleteMapping("book/{bookId}")
    public Result deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.delete(bookId);
        return ResultUtils.success();
    }

    @PostMapping("books/delete")
    public Result deleteBook(@RequestBody List<Integer> bookIds) {
        bookService.delete(bookIds);
        return ResultUtils.success();
    }

    @GetMapping("book/{bookId}")
    public Result showDetail(@PathVariable("bookId") Integer bookId) {
        BookInfo bookInfo = bookService.showDetail(bookId);
        return ResultUtils.success(bookInfo);
    }

    @GetMapping("books")
    public Result<PageInfo<BookInfo>> books(PageSearcher searcher) {
        PageInfo<BookInfo> result = bookService.listBookInfo(searcher);
        return ResultUtils.success(result);
    }

}
