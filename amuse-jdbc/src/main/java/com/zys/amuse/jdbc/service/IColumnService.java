package com.zys.amuse.jdbc.service;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.ColumnInfo;

import java.util.List;

/**
 * Created by zhongjunkai on 18/12/3.
 */
public interface IColumnService {

    void add(ColumnInfo columnInfo);

    void update(ColumnInfo columnInfo);

    void delete(Integer colId);

    void deleteBySheetId(Integer sheetId);

    ColumnInfo showDetail(Integer colId);

    PageInfo<ColumnInfo> listColumn(Integer pageNum, Integer pageSize);

    PageInfo<ColumnInfo> listColumn(Integer pageNum, Integer pageSize, Integer sheetId);


}
