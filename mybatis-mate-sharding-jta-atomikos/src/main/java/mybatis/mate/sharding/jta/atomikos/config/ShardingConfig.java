package mybatis.mate.sharding.jta.atomikos.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import mybatis.mate.config.DataSourceProperty;
import mybatis.mate.provider.AtomikosDataSourceProvider;
import mybatis.mate.provider.IDataSourceProvider;
import org.postgresql.xa.PGXADataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.XADataSource;
import java.sql.SQLException;

@Configuration
public class ShardingConfig {

    /**
     * 可注释 @bean 注解测试无效事务
     */
    @Bean
    public IDataSourceProvider dataSourceProvider() {
        // jta atomikos 分布式事务
        return new AtomikosDataSourceProvider() {

            /**
             * 创建 XADataSource 数据源
             *
             * @param group              数据库分组
             * @param dataSourceProperty 数据源配置
             * @return
             */
            @Override
            public XADataSource createXADataSource(String group, DataSourceProperty dataSourceProperty) throws SQLException {
                // 根据数据库类型可以创建指定 XA 数据源
                final String driverClassName = dataSourceProperty.getDriverClassName();
                System.out.println("数据库类型：" + driverClassName);
                if (driverClassName.contains("postgresql")) {
                    // postgresql xa
                    PGXADataSource pgxaDataSource = new PGXADataSource();
                    pgxaDataSource.setUrl(dataSourceProperty.getUrl());
                    pgxaDataSource.setUser(dataSourceProperty.getUsername());
                    pgxaDataSource.setPassword(dataSourceProperty.getPassword());
                    return pgxaDataSource;
                }

                // mysql xa
                MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
                mysqlXaDataSource.setUrl(dataSourceProperty.getUrl());
                mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
                mysqlXaDataSource.setUser(dataSourceProperty.getUsername());
                mysqlXaDataSource.setPassword(dataSourceProperty.getPassword());
                mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
                return mysqlXaDataSource;
            }
        };
    }
}

