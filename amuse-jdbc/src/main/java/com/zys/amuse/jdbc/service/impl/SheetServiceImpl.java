package com.zys.amuse.jdbc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.dao.SheetInfoMapper;
import com.zys.amuse.jdbc.entity.SheetInfo;
import com.zys.amuse.jdbc.service.ISheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjunkai on 18/12/3.
 */
@Service
public class SheetServiceImpl implements ISheetService{

    @Autowired
    private SheetInfoMapper sheetInfoMapper;

    @Override
    public Integer add(SheetInfo sheetInfo) {
        sheetInfoMapper.insertSelective(sheetInfo);
        return sheetInfo.getId();
    }

    @Override
    public void update(SheetInfo sheetInfo) {
        sheetInfoMapper.updateByPrimaryKeySelective(sheetInfo);
    }

    @Override
    public void delete(Integer sheetId) {
        sheetInfoMapper.deleteByPrimaryKey(sheetId);
    }

    @Override
    public void deleteByBookId(Integer bookId) {
        sheetInfoMapper.deleteByBookId(bookId);
    }

    @Override
    public SheetInfo showDetail(Integer sheetId) {
        return sheetInfoMapper.selectByPrimaryKey(sheetId);
    }

    @Override
    public PageInfo<SheetInfo> listSheet(Integer pageNum, Integer pageSize) {
        PageInfo<SheetInfo> result = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> sheetInfoMapper.listSheet());
        return result;
    }

    @Override
    public PageInfo<SheetInfo> listSheet(Integer pageNum, Integer pageSize, Integer bookId) {
        PageInfo<SheetInfo> result = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> sheetInfoMapper.listSheetByBookId(bookId));
        return result;
    }
}
