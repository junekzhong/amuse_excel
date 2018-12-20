package com.zys.amuse.jdbc;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.SheetInfo;
import com.zys.amuse.jdbc.service.ISheetService;
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
 * Created by zhongjunkai on 18/12/3.
 */
@SpringBootTest(classes = TestBookService.class)
@RunWith(SpringRunner.class)
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.zys.amuse")
public class TestSheetService {

    @Autowired
    private ISheetService sheetService;

    @Test
    public void add() {
        SheetInfo sheetInfo = new SheetInfo();
        sheetInfo.setBookId(8);
        sheetInfo.setSheetName("测试sheet");
        sheetInfo.setSheetOrder(0);
        Integer id = sheetService.add(sheetInfo);
        Assert.assertTrue(6 == id);
    }

    @Test
    public void show() {
        SheetInfo sheetInfo = sheetService.showDetail(1);
        Assert.assertNotNull(sheetInfo);
    }

    @Test
    public void update() {
        SheetInfo sheetInfo = sheetService.showDetail(3);
        sheetInfo.setSheetName("测试sheet2");
        sheetService.update(sheetInfo);
        Assert.assertEquals(sheetService.showDetail(3).getSheetName(), "测试sheet2");
    }

    @Test
    public void list() {
        PageInfo data = sheetService.listSheet(1, 5);
        Assert.assertTrue(data.getList().size() > 0);
    }

    @Test
    public void delete() {
        sheetService.delete(2);
        Assert.assertNull(sheetService.showDetail(2));
    }

    @Test
    public void deleteByBookId() {
        sheetService.deleteByBookId(8);
        Assert.assertTrue(sheetService.listSheet(1, 5).getList().size() == 0);
    }
}
