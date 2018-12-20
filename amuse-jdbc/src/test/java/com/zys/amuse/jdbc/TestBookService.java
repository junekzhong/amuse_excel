package com.zys.amuse.jdbc;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.BookInfo;
import com.zys.amuse.jdbc.helper.PageSearcher;
import com.zys.amuse.jdbc.service.IBookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhongjunkai on 18/11/30.
 */
@SpringBootTest(classes = TestBookService.class)
@RunWith(SpringRunner.class)
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.zys.amuse")
public class TestBookService {

    @Autowired
    private IBookService bookService;

    @Test
    public void insert() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName("测试Book1");
        Integer id = bookService.add(bookInfo);
        Assert.assertTrue(id > 1);
    }

    @Test
    public void show() {
        BookInfo bookInfo = bookService.showDetail(8);
        Assert.assertNotNull(bookInfo);
    }

    @Test
    public void update() {
        BookInfo bookInfo = bookService.showDetail(8);
        bookInfo.setBookName("测试book8");
        bookService.update(bookInfo);
        Assert.assertTrue(bookService.showDetail(8).getBookName().equals("测试book8"));
    }

    @Test
    public void list() {
        PageSearcher searcher = new PageSearcher();
        searcher.setPageNum(1);
        searcher.setPageSize(5);
        PageInfo<BookInfo> data = bookService.listBookInfo(searcher);
        Assert.assertTrue(data.getList().size() > 0);
    }

    @Test
    public void delete() {
        bookService.delete(6);
        Assert.assertNull(bookService.showDetail(6));
    }
}
