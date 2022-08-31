package mybatis.mate.sharding.config;

import mybatis.mate.sharding.IShardingProcessor;
import mybatis.mate.sharding.ShardingKey;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;

/**
 * 这个类可以自行控制 datasourceKey
 */
@Component
public class MyShardingProcessor implements IShardingProcessor {

    @Override
    public boolean changeDatasource(Invocation invocation, MappedStatement mappedStatement, String datasourceKey) {
        System.out.println("ThreadLocal 缓存 datasourceKey = " + datasourceKey);

        // 这里返回 true 将切换到指定 datasourceKey 对应数据源，返回 false 按默认规则自动切换
        // 切换到指定 datasourceKey 数据源调用 ShardingKey.change("mysqlt2"); 记得返回 true 切换成功

        return false;
    }

    @Override
    public void clearDatabaseKey(Invocation invocation, MappedStatement mappedStatement, String datasourceKey) {
        System.out.println("清理 ThreadLocal 缓存 datasourceKey = " + datasourceKey);

        // 如果要保持 datasourceKey 一直在一个线程中可以不用清理，以下代码删除即可
        ShardingKey.clearDatabaseKey();
    }
}
