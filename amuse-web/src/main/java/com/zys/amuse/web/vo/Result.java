package com.zys.amuse.web.vo;

import lombok.Data;

/**
 * 同一返回结果封装类
 *
 * Created by zhongjunkai on 17/11/27.
 */
@Data
public class Result<T> {

    /**
     * 错误码
     */
    private String code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 结果数据
     */
    private T data;


}
