# amuse
> 封装了excel导入导出等功能。通过自定义验证器，来验证单元格的内容是否正确；同时可以设置异常处理策略，使得可以在验证器发现单元格
内容有异常时采用对应的处理策略，目前有两种处理策略，interrupt(即时中断)和fluency(发生异常继续处理，后续统一处理)。
## 如何使用
> 使用该项目时，只需要引入amuse-core到需要使用excel功能的项目中即可，支持spring与springboot的整合，下面展示与springboot整合的过程。<br>

1.自定义一个ExcelProcessor，该类必须继承AbstractExcelProcessor类
---
```
import com.zys.amuse.basis.ColumnInfo;
import com.zys.amuse.basis.DataHandler;
import com.zys.amuse.excel.AbstractExcelProcessor;
import com.zys.amuse.excel.valid.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * excel处理器
 *
 * Created by zhongjunkai on 18/12/10.
 */
public class ExcelProcessor extends AbstractExcelProcessor{

    /**
     * 异常处理策略{@link com.zys.amuse.excel.ErrorPolicy}
     */
    private String errorPolicy;
    
    /**
     * 构造器
     * @param dataHandler 数据持久化操作类，类似于DAO或Service
     * @param errorPolicy 持久化策略，目前支持两种类型，详情见{@link com.zys.amuse.excel.ErrorPolicy}
     */
    public ExcelProcessor(DataHandler dataHandler, String errorPolicy) {
        super(dataHandler);
        this.errorPolicy = errorPolicy;
    }

    /**
     * 获取单元格对应的验证器(默认是返回空)，具体的逻辑自定义实现(可选，如果没有验证器，可以不用重写该方法)
     * 
     * @param tableName 数据源表
     * @param col 对应的字段
     * @return
     */
    @Override
    protected List<Validator> getCellValidators(String tableName, ColumnInfo col) {
        //todo 可以从数据库或其他途径获取验证器
        return new ArrayList<>();
    }

    /**
     * 返回当前的异常策略
     * 
     * @return
     */
    @Override
    protected String errorPolicy() {
        return this.errorPolicy;
    }
}
```
2.定义一个数据持久化类
---
```
import com.zys.amuse.basis.AbstractDataHandler;

import java.util.List;
import java.util.Map;

/**
 * 具体的数据处理类，可以将保存到数据库的步骤放到这里来实现，该类实现了通过jdbc的方式保存数据
 *
 * Created by zhongjunkai on 18/11/19.
 */
public class JdbcDataHandler extends AbstractDataHandler {

    @Override
    public void persistent(String table, List<Map<String, Object>> datas) {
        //todo 持久化数据
    }
}
```
3.将ExcelProcessor交由spring管理
---
```
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
```
经过以上三步，完成了excel操作对象的初始化，接下来就可以在controller中使用具体的功能了，关于controller中的代码可以参考amuse-web模块中
TestController的代码。<br>
## 其他
* 1.该模块还有一个用vue写的前端项目，后续会上传相关的代码
