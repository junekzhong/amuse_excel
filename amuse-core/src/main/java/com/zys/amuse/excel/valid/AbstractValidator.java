package com.zys.amuse.excel.valid;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 抽象验证器，实现了验证器接口，该类中通过传入{@link ValidateRule}对象，来实现自定义验证规则
 *
 * Created by zhongjunkai on 18/11/20.
 */
public abstract class AbstractValidator implements Validator {

    protected String rule;

    protected String msg;

    public AbstractValidator(String rule, String msg) {
        this.rule = rule;
        this.msg = msg;
    }

    public AbstractValidator(String msg) {
        this.msg = msg;
    }

}
