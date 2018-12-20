package com.zys.amuse.basis;

/**
 * excel sheet抽象
 *
 * Created by zhongjunkai on 18/11/16.
 */
public abstract class AbstractBookSheet {

    /**
     * 获取sheet名称
     *
     * @return
     */
    protected abstract String getSheetName();

    /**
     * 获取sheet顺序
     *
     * @return
     */
    protected abstract int getSheetOrder();


}
