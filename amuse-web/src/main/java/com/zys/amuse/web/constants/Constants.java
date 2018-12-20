package com.zys.amuse.web.constants;

/**
 * Created by zhongjunkai on 17/11/27.
 */
public class Constants {

    public enum ResultEnums{
        SUCCESS("1", "成功"),
        ERROR("0", "发生异常");

        private String code;

        private String msg;

        ResultEnums(String code, String msg){
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
