package com.zys.amuse.jdbc.util;

import com.github.pagehelper.PageHelper;

import java.util.Properties;

/**
 * 分页工具类
 *
 * Created by zhongjunkai on 18/6/22.
 */
public class PageHelperUtils {

    public static PageHelper createPageHelperPlugin(){
        //分页插件,插件无非是设置mybatis的拦截器
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
