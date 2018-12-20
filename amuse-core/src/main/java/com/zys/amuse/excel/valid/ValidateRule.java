package com.zys.amuse.excel.valid;


/**
 * 验证规则，用于用户自定义的字段规则，结合{@link Validator}来实现具体的验证流程，
 * 可将验证规则通过数据库或者其他的方式进行保存
 *
 * Created by zhongjunkai on 18/11/20.
 */
public class ValidateRule {

    /**
     * 验证规则表达式
     */
    private String rule;

    /**
     * 描述
     */
    private String msg;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ValidateRule(String rule, String msg) {
        this.rule = rule;
        this.msg = msg;
    }

    public ValidateRule() {

    }
}
