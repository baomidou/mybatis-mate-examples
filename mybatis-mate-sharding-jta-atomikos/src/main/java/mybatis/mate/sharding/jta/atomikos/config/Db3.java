package mybatis.mate.sharding.jta.atomikos.config;

import com.baomidou.mybatisplus.extension.ddl.IDdl;
import com.baomidou.mybatisplus.extension.ddl.history.IDdlGenerator;
import com.baomidou.mybatisplus.extension.ddl.history.PostgreDdlGenerator;
import lombok.AllArgsConstructor;
import mybatis.mate.sharding.ShardingDatasource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
public class Db3 implements IDdl {
    private ShardingDatasource shardingDatasource;

    @Override
    public void runScript(Consumer<DataSource> consumer) {
        consumer.accept(shardingDatasource.getDataSource("test3t1"));
    }

    /**
     * 执行 SQL 脚本方式
     */
    @Override
    public List<String> getSqlFiles() {
        return Arrays.asList("db/log-db3.sql");
    }

    /**
     * 该方法也可以不指定 ddl 生成器默认自动识别
     */
    @Override
    public IDdlGenerator getDdlGenerator() {
        // 指定数据库 ddl 创建处理器
        return PostgreDdlGenerator.newInstance();
    }
}
