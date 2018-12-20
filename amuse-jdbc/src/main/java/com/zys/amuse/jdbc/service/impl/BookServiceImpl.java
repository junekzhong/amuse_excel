package com.zys.amuse.jdbc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zys.amuse.jdbc.dao.BookInfoMapper;
import com.zys.amuse.jdbc.entity.BookInfo;
import com.zys.amuse.jdbc.helper.PageSearcher;
import com.zys.amuse.jdbc.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhongjunkai on 18/12/3.
 */
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public Integer add(BookInfo bookInfo) {
        bookInfoMapper.insertSelective(bookInfo);
        return bookInfo.getId();
    }

    @Override
    public void update(BookInfo bookInfo) {
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
    }

    @Override
    public void delete(Integer bookId) {
        bookInfoMapper.deleteByPrimaryKey(bookId);
    }

    @Override
    public void delete(List<Integer> bookIds) {
        if(!CollectionUtils.isEmpty(bookIds)) {
            bookIds.forEach(bookId -> this.delete(bookId));
        }
    }

    @Override
    public BookInfo showDetail(Integer bookId) {
        return bookInfoMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public PageInfo<BookInfo> listBookInfo(PageSearcher searcher) {
        PageInfo<BookInfo> result = PageHelper.startPage(searcher.getPageNum(), searcher.getPageSize())
                .doSelectPageInfo(() -> bookInfoMapper.listBook());
        return result;
    }


}
