package com.zys.amuse.jdbc;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.ColumnInfo;
import com.zys.amuse.jdbc.service.IColumnService;
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
public class TestColumnService {

    @Autowired
    private IColumnService columnService;

    @Test
    public void add() {
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setColName("col1");
        columnInfo.setColComment("字段1");
        columnInfo.setColOrder(0);
        columnInfo.setColType("varchar");
        columnInfo.setSheetId(9);
        columnService.add(columnInfo);
    }

    @Test
    public void update() {
        ColumnInfo col = columnService.showDetail(1);
        col.setColName("字段one");
        columnService.update(col);
        Assert.assertEquals("字段one", col.getColName());
    }

    @Test
    public void show() {
        ColumnInfo col = columnService.showDetail(1);
        Assert.assertNotNull(col);
    }

    @Test
    public void list() {
        PageInfo data = columnService.listColumn(1, 10);
        Assert.assertNotNull(data.getList());
    }

    @Test
    public void delete() {
        columnService.delete(1);
        Assert.assertNull(columnService.showDetail(1));
    }

    @Test
    public void deleteBySheetId() {
        columnService.deleteBySheetId(9);
        Assert.assertEquals(0, columnService.listColumn(1, 10).getList().size());
    }
}
