package com.zys.amuse.excel;

import com.alibaba.fastjson.JSON;
import com.zys.amuse.basis.AbstractDataHandler;

import java.util.List;
import java.util.Map;

/**
 * 具体的数据处理类，可以将保存到数据库的步骤放到这里来实现，该类实现了通过jdbc的方式保存数据
 *
 * Created by zhongjunkai on 18/11/19.
 */
public class JdbcDataHandler extends AbstractDataHandler {

    @Override
    public void persistent(String table, List<Map<String, Object>> datas) {
        System.out.println("处理的table：" + table);
        System.out.println("处理的数据：" + JSON.toJSONString(datas));
    }
}
