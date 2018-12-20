package com.zys.amuse.excel.valid.validator;

import com.zys.amuse.excel.utils.CellUtils;
import com.zys.amuse.excel.utils.JodaTimeUtils;
import com.zys.amuse.excel.valid.AbstractValidator;
import com.zys.amuse.excel.valid.ValidState;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

/**
 * 日期范围验证器，用于验证日期类型单元格的值是否在指定日期范围之内，表达式为begin(yyyy-MM-dd)|end(yyyy-MM-dd)，
 * 如果begin和end同时存在，则比较是否在两个值区间范围，如果begin为空，则比较<end的值，如果end为空，则比较 >= end的值，
 * 如果是空字符串则直接返回true
 *
 * Created by zhongjunkai on 18/11/20.
 */
@Deprecated
public class DateRangeValidator extends AbstractValidator {

    public DateRangeValidator(String rule, String msg) {
        super(rule, msg);
    }

    public DateRangeValidator(String msg) {
        super(msg);
    }

    @Override
    public ValidState isValidWithErrorCell(Cell cell) {
        String ruleExp = "";
        String[] rules = ruleExp.split("\\|");
        ValidState validState = new ValidState();
        if(null != rules) {
            //单元格的日期值
            Date value = (Date) CellUtils.getCellValue(cell);
            //范围开始时间
            String begin = ruleExp.split("\\|")[0];
            Date beginDate = null;
            if(!"".equals(begin)) {
                //解析起始时间字符串
                beginDate = JodaTimeUtils.parseStandDate(begin);
            }
            //返回结果
            boolean result = true;
            //如果规则数组长度等于2，说明同时规定了最大时间和最小时间
            if(rules.length == 2) {
                String end = rules[1];
                Date endDate = null;
                if(!"".equals(end)) {
                    endDate = JodaTimeUtils.parseStandDate(end);
                }
                if(endDate != null) {
                    //比较单元格日期值是否小于最大值
                    result = value.getTime() < endDate.getTime() && result;
                }
            }
            //如果规则数组的长度不等于2，则只比较最小日期值
            if(beginDate != null) {
                //比较单元格日期值是否大于等于最小值
                result = result && value.getTime() >= beginDate.getTime();
            }
            validState.setValid(result);
            validState.setMsg(msg);
        }
        return validState;
    }

    @Override
    public ValidState isValidWithError(Object value) {
        return null;
    }
}
