package com.zys.amuse.excel.valid.validator;

import com.zys.amuse.excel.utils.CellUtils;
import com.zys.amuse.excel.valid.AbstractValidator;
import com.zys.amuse.excel.valid.ValidState;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 非空验证器，用来校验单元格是否为空
 *
 * Created by zhongjunkai on 18/12/11.
 */
public class RequiredValidator extends AbstractValidator {

    private static final String regStr = "\\{required\\s*:\\s*(true|false)(\\s*,\\s*)?(msg\\s*:\\s*'(.*)')?\\}";

    public RequiredValidator(String msg) {
        super(msg);
    }

    @Override
    public ValidState isValidWithErrorCell(Cell cell) {
        Object value = CellUtils.getCellValue(cell);
        return this.isValidWithError(value);
    }

    @Override
    public ValidState isValidWithError(Object value) {
        //如果值为空
        if(null == value || value.equals("")) {
            return new ValidState(Boolean.FALSE, msg);
        }
        return new ValidState();
    }
}
