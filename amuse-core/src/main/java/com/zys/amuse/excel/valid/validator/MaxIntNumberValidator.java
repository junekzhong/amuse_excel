package com.zys.amuse.excel.valid.validator;

import com.zys.amuse.excel.utils.CellUtils;
import com.zys.amuse.excel.valid.AbstractValidator;
import com.zys.amuse.excel.valid.ValidState;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 整数的最大值
 * Created by zhongjunkai on 18/11/20.
 */
@Deprecated
public class MaxIntNumberValidator extends AbstractValidator {

    public MaxIntNumberValidator(String rule, String msg) {
        super(rule, msg);
    }

    public MaxIntNumberValidator(String msg) {
        super(msg);
    }

    @Override
    public ValidState isValidWithErrorCell(Cell cell) {
        Object v = CellUtils.getCellValue(cell);
        boolean valid = true;
        //如果是数字类型
        if(v instanceof Double) {
            int vv = ((Double) v).intValue();
            valid = vv <= Integer.parseInt("");
        } else if(v instanceof String) {//如果是字符串类型
            int vv = Integer.parseInt((String)v);
            valid = vv <= Integer.parseInt(rule);
        }
        if(!valid) {
            return new ValidState(valid, msg);
        }
        return new ValidState();
    }

    @Override
    public ValidState isValidWithError(Object value) {
        return null;
    }
}
