package com.zys.amuse.excel;

import com.zys.amuse.basis.ColumnInfo;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 导入接口
 *
 * Created by zhongjunkai on 18/11/16.
 */
public interface Importer {


    /**
     * 导入文件数据
     * @param inputStream 导入文件流
     * @param outputStream 异常数据输出流
     * @param dataTable 导入数据的数据表名称
     * @param headers 数据表字段信息
     */
    void importResource(InputStream inputStream, OutputStream outputStream, String dataTable, List<ColumnInfo> headers);

    /**
     * 导入文件数据，根据文件名来推断数据表名称，所以文件名必须要跟导入的数据表表名一直
     *
     * @param file 导入的数据文件
     * @param outputStream 异常数据输出流
     * @param headers 数据表字段信息
     */
    void importResource(File file, OutputStream outputStream, List<ColumnInfo> headers);

}
