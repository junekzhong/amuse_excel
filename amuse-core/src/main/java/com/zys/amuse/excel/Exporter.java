package com.zys.amuse.excel;

import com.zys.amuse.basis.ColumnInfo;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 导出数据接口,定义了数据导出的方法
 *
 * Created by zhongjunkai on 18/11/20.
 */
public interface Exporter {

    /**
     * 数据导出
     * @param out 输出流
     * @param headers 数据来源表对应的excel表头
     * @param data 数据来源表的数据
     */
    void exportResource(OutputStream out, List<ColumnInfo> headers, List<Map<String, Object>> data);
}
