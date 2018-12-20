package com.zys.amuse.excel.exception;

/**
 * 规则异常
 *
 * Created by zhongjunkai on 18/12/12.
 */
public class IllegalRuleException extends RuntimeException{

    public IllegalRuleException() {
        super("非法的验证规则");
    }

    public IllegalRuleException(String message) {
        super(message);
    }
}
