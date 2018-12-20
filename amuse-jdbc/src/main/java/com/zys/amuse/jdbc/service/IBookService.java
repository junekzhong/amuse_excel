package com.zys.amuse.jdbc.service;

import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.entity.BookInfo;
import com.zys.amuse.jdbc.helper.PageSearcher;

import java.util.List;

/**
 * Created by zhongjunkai on 18/12/3.
 */
public interface IBookService {

    Integer add(BookInfo bookInfo);

    void update(BookInfo bookInfo);

    void delete(Integer bookId);

    void delete(List<Integer> bookIds);

    BookInfo showDetail(Integer bookId);

    PageInfo<BookInfo> listBookInfo(PageSearcher searcher);
}
