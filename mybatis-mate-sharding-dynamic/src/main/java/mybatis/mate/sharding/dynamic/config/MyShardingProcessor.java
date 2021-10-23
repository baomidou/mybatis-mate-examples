package mybatis.mate.sharding.dynamic.config;

import mybatis.mate.sharding.IShardingProcessor;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

@Component
public class MyShardingProcessor implements IShardingProcessor {

    /**
     * 切换数据源，返回 false 使用默认数据源切换规则
     *
     * @param args            执行参数
     * @param mappedStatement {@link MappedStatement}
     * @param datasourceKey   数据源关键字
     * @return true 成功  false 失败
     */
    @Override
    public boolean changeDatasource(Object[] args, MappedStatement mappedStatement,
                                    String datasourceKey) {
        System.err.println(" 执行方法：" + mappedStatement.getId());
        System.err.println(" datasourceKey = " + datasourceKey);
        // 如果想自定义控制切换那个数据源可以在此方法中处理
        // 返回 true 则按照你的切换方案执行 false 默认规则切换 @Sharding 注解才有效
        // datasourceKey = null 时候 mate 底层依然会使用默认数据源
        return true;
    }
}
