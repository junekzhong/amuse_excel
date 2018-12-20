package com.zys.amuse.web.config;

import com.alibaba.fastjson.JSON;
import com.zys.amuse.basis.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjunkai on 18/12/10.
 */
@Component
public class CustomDataHandler implements DataHandler{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void persistent(String repository, List<Map<String, Object>> datas) {
        String sql = "insert into test (code, name, create_time, sort_num) values(:code, :name, :create_time, :sort_num)";
        if(!CollectionUtils.isEmpty(datas)) {
            for(Map<String, Object> data : datas) {
                jdbcTemplate.update(sql, data);
            }
        }
        System.out.println(JSON.toJSONString(datas));
    }
}
