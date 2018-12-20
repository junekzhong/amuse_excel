package com.zys.amuse.basis;

import java.util.List;
import java.util.Map;

/**
 * 数据处理接口，定义了处理excel数据的方法，通过实现该方法，可以将excel数据持久化到各种存储中
 *
 * Created by zhongjunkai on 18/11/19.
 */
public interface DataHandler {

    /**
     * 持久化excel数据
     *
     * @param repository 要保存的仓库(数据表)
     * @param datas excel数据
     */
    void persistent(String repository, List<Map<String, Object>> datas);

}
