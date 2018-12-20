package com.zys.amuse.basis;

import java.util.List;

/**
 * excel抽象，可包含多个sheet
 *
 * Created by zhongjunkai on 18/11/16.
 */
public abstract class AbstractBook {

    /**
     * 获取excel的名称
     *
     * @return
     */
    protected abstract String getBookName();

    /**
     * 获取所有的sheet
     *
     * @return
     */
    protected abstract List<AbstractBookSheet> getSheets();
}
