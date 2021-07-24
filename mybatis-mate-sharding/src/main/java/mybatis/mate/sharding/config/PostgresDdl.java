package mybatis.mate.sharding.config;

import mybatis.mate.ddl.IDdl;
import mybatis.mate.sharding.ShardingKey;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Component
public class PostgresDdl implements IDdl {

    @Override
    public void sharding(Consumer<IDdl> consumer) {
        // 多数据源指定，主库初始化从库自动同步
        // postgrest2 = postgres（数据源group） + t1（数据源key）
        ShardingKey.change("postgrest1");
        consumer.accept(this);
    }

    /**
     * 执行 SQL 脚本方式
     */
    @Override
    public List<String> getSqlFiles() {
        return Arrays.asList(
                "db/user-postgres.sql"
                // ,"db/user-data.sql"
        );
    }
}
