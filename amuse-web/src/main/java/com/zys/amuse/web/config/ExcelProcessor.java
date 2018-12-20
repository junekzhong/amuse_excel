package com.zys.amuse.web.config;

import com.zys.amuse.basis.ColumnInfo;
import com.zys.amuse.basis.DataHandler;
import com.zys.amuse.excel.AbstractExcelProcessor;
import com.zys.amuse.excel.valid.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel处理器
 *
 * Created by zhongjunkai on 18/12/10.
 */
public class ExcelProcessor extends AbstractExcelProcessor{


    private String errorPolicy;

    public ExcelProcessor(DataHandler dataHandler, String errorPolicy) {
        super(dataHandler);
        this.errorPolicy = errorPolicy;
    }

    @Override
    protected List<Validator> getCellValidators(String tableName, ColumnInfo col) {
        return new ArrayList<>();
    }

    @Override
    protected String errorPolicy() {
        return this.errorPolicy;
    }
}
