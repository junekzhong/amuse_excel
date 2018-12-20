package com.zys.amuse.excel.valid;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 验证器接口，定义了验证单元格格式的方法，是单元格验证的超基类，可以通过实现该接口来自定义验证器；
 * 另外，提供了一个抽象类{@link AbstractValidator}，也可以通过集成该抽象类完成自定义验证器；
 * 系统内置了一批验证器如{@link com.zys.amuse.excel.valid.validator.RequiredValidator}等。
 *
 * Created by zhongjunkai on 18/11/16.
 */
public interface Validator {

    /**
     * 验证方法，可用于验证每个单元格的值是否符合一定的规范等
     *
     * @param cell 要验证的单元格
     * @return
     */
    ValidState isValidWithErrorCell(Cell cell);

    /**
     * 验证方法，用于验证指定的值是否符合规则
     *
     * @param value
     * @return
     */
    ValidState isValidWithError(Object value);
}
