package com.zys.amuse.excel.valid;

/**
 * 验证异常返回结果，在验证完单元格之后返回的对象，如果单元格内容验证未通过，则valid属性置为false，且将
 * 错误提示通过msg返回
 *
 * Created by zhongjunkai on 18/11/20.
 */
public class ValidState {

    /**
     * 验证结果，ture：通过，false：未通过
     */
    private boolean valid = true;

    /**
     * 错误提示(可从{@link ValidateRule}对象中获得)
     */
    private String msg;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ValidState(boolean valid, String msg) {
        this.valid = valid;
        this.msg = msg;
    }

    public ValidState() {
    }
}
