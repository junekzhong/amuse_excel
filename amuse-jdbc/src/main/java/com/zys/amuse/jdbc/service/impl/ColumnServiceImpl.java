package com.zys.amuse.jdbc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.dao.ColumnInfoMapper;
import com.zys.amuse.jdbc.entity.ColumnInfo;
import com.zys.amuse.jdbc.service.IColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjunkai on 18/12/3.
 */
@Service
public class ColumnServiceImpl implements IColumnService{

    @Autowired
    private ColumnInfoMapper columnInfoMapper;

    @Override
    public void add(ColumnInfo columnInfo) {
        columnInfoMapper.insertSelective(columnInfo);
    }

    @Override
    public void update(ColumnInfo columnInfo) {
        columnInfoMapper.updateByPrimaryKeySelective(columnInfo);
    }

    @Override
    public void delete(Integer colId) {
        columnInfoMapper.deleteByPrimaryKey(colId);
    }

    @Override
    public void deleteBySheetId(Integer sheetId) {
        columnInfoMapper.deleteBySheetId(sheetId);
    }

    @Override
    public ColumnInfo showDetail(Integer colId) {
        return columnInfoMapper.selectByPrimaryKey(colId);
    }

    @Override
    public PageInfo<ColumnInfo> listColumn(Integer pageNum, Integer pageSize) {
        PageInfo<ColumnInfo> result = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> columnInfoMapper.listColumn());
        return result;
    }

    @Override
    public PageInfo<ColumnInfo> listColumn(Integer pageNum, Integer pageSize, Integer sheetId) {
        PageInfo<ColumnInfo> result = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> columnInfoMapper.listColumnBySheetId(sheetId));
        return result;
    }
}
