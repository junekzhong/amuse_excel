package com.zys.amuse.excel.valid.validator;

import com.zys.amuse.excel.exception.IllegalRuleException;
import com.zys.amuse.excel.utils.CellUtils;
import com.zys.amuse.excel.valid.AbstractValidator;
import com.zys.amuse.excel.valid.ValidState;
import org.apache.poi.ss.usermodel.Cell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 最大长度验证器
 *
 * Created by zhongjunkai on 18/12/12.
 */
public class MaxLengthValidator extends AbstractValidator {

    private static final String regStr = "\\{maxLength\\s*:\\s*(\\d)+(\\s*,\\s*)?(msg\\s*:\\s*'(.*)')?\\}";

    private static final Pattern pattern = Pattern.compile(regStr);

    public MaxLengthValidator(String rule, String msg) {
        super(rule, msg);
    }



    @Override
    public ValidState isValidWithErrorCell(Cell cell) {
        Object value = CellUtils.getCellValue(cell);
        return this.isValidWithError(value);
    }

    @Override
    public ValidState isValidWithError(Object value) {
        if(null == value || "".equals(value)) {
            return new ValidState();
        }
        //将值转换为字符串
        String vs = String.valueOf(value);
        Matcher matcher = pattern.matcher(rule);
        if(matcher.find()) {
            //规则定义的最大值
            Integer maxLength = Integer.valueOf(matcher.group(1));
            //获取规则定义的msg
            String msgIn = matcher.group(4);
            //如果单元格的值长度大于最大程度
            if(vs.length() > maxLength) {
                return new ValidState(Boolean.FALSE, "".equals(msg) ? msgIn : msg);
            }
        } else {
            throw new IllegalRuleException();
        }
        return new ValidState();
    }
}
