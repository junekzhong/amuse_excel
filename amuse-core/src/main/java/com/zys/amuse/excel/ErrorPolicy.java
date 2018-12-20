package com.zys.amuse.excel;

/**
 * 错误处理策略，用于在导入数据发生验证异常时的处理策略
 *
 * Created by zhongjunkai on 18/11/20.
 */
public enum ErrorPolicy {

    INTERRUPT("interrupt"),//一旦发生验证失败，马上中断当前的导入，返回发生异常的数据
    FLUENCY("fluency");//在发生异常时继续进行数据导入，将所有正常数据完成之后返回所有有异常的数据

    private String value;

    ErrorPolicy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
