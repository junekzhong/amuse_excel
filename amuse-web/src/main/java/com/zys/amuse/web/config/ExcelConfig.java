package com.zys.amuse.web.config;

import com.zys.amuse.basis.DataHandler;
import com.zys.amuse.excel.ErrorPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhongjunkai on 18/12/10.
 */
@Configuration
public class ExcelConfig {

    @Bean
    public ExcelProcessor excelProcessor(DataHandler dataHandler) {
        ExcelProcessor excelProcessor = new ExcelProcessor(dataHandler, ErrorPolicy.FLUENCY.getValue());
        return excelProcessor;
    }
}
