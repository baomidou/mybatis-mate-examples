package mybatis.mate.sharding.dynamic.config;

import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class MyDatabaseIdProvider implements DatabaseIdProvider {

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        String jdbcUrl = dataSource.getConnection().getMetaData().getURL();
        return JdbcUtils.getDbType(jdbcUrl).getDb();
    }
}
