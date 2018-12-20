package com.zys.amuse.jdbc.service;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.SheetInfo;

import java.util.List;

/**
 * Created by zhongjunkai on 18/12/3.
 */
public interface ISheetService {

    Integer add(SheetInfo sheetInfo);

    void update(SheetInfo sheetInfo);

    void delete(Integer sheetId);

    void deleteByBookId(Integer bookId);

    SheetInfo showDetail(Integer sheetId);

    PageInfo<SheetInfo> listSheet(Integer pageNum, Integer pageSize);

    PageInfo<SheetInfo> listSheet(Integer pageNum, Integer pageSize, Integer bookId);

}
