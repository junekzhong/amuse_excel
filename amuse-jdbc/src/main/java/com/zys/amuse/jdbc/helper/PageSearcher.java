package com.zys.amuse.jdbc.helper;

import lombok.Data;

/**
 * 分页查询条件封装类
 *
 * Created by zhongjunkai on 18/6/1.
 */
@Data
public class PageSearcher {

    private int pageNum = 1;

    private int pageSize = 10;

}
