package com.zys.amuse.excel.valid.validator;

import com.zys.amuse.excel.utils.CellUtils;
import com.zys.amuse.excel.valid.AbstractValidator;
import com.zys.amuse.excel.valid.ValidState;
import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期字符串验证器(好像用处不大=。=！)
 *
 * Created by zhongjunkai on 18/12/13.
 */
public class DateFormatValidator extends AbstractValidator {

    private static Map<String, String> datePatterns = new HashMap<String, String>(){{
        put("yyyy-MM-dd", "((1|[2-9])\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|((1|2)[0-9])|3[0-1])");
        put("yyyy-MM-dd HH:mm:ss", "((1|[2-9])\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|((1|2)[0-9])|3[0-1])(\\s*)(1[0-9]:?\\[0-6][0-9]:?\\[0-6][0-9])");
        put("yyyy/MM/dd", "((1|[2-9])\\d{3})/(0[1-9]|1[0-2])/(0[1-9]|((1|2)[0-9])|3[0-1])");
        put("yyyy/MM/dd HH:mm:ss", "((1|[2-9])\\d{3})/(0[1-9]|1[0-2])/(0[1-9]|((1|2)[0-9])|3[0-1])(\\s*)(1[0-9]:?\\[0-6][0-9]:?\\[0-6][0-9])");
        put("yyyyMMdd", "((1|[2-9])\\d{3})(0[1-9]|1[0-2])(0[1-9]|((1|2)[0-9])|3[0-1])");
        put("yyyyMMddHHmmss", "((1|[2-9])\\d{3})(0[1-9]|1[0-2])(0[1-9]|((1|2)[0-9])|3[0-1])(1[0-9]\\[0-6][0-9]\\[0-6][0-9])");
    }};

    public DateFormatValidator(String rule, String msg) {
        super(rule, msg);
    }

    @Override
    public ValidState isValidWithErrorCell(Cell cell) {
        Object cellValue = CellUtils.getCellValue(cell);
        return this.isValidWithError(cellValue);
    }

    @Override
    public ValidState isValidWithError(Object value) {
        //如果日期正则
        String regx = datePatterns.get(rule);
        String dateStr = (String) value;
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(dateStr);
        //匹配成功
        if(matcher.find()) {
            return new ValidState();
        }
        return new ValidState(Boolean.FALSE, msg);
    }
}
