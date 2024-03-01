package mybatis.mate.sharding.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybatis.mate.sharding.ShardingHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 如果外部未调用 spring boot 的健康检查 <a href="http://localhost:8080/actuator/health">...</a>
 * <p>
 * 可以如下使用定时任务检查数据库健康状况，这个方法不是必须的
 */
@Slf4j
@Component
@EnableScheduling   // 这个开启定时任务，注意请勿重复设置
@AllArgsConstructor
public class ScheduledConfig {
    private ShardingHealthIndicator shardingHealthIndicator;

    /**
     * 每 15 秒执行一次，健康检查
     */
    @Scheduled(cron = "0/15 * * * * ? ")
    public void dbHealthCheck() {
        Health health = shardingHealthIndicator.health();
        log.debug("Sharding health: {}", health.toString());
    }
}
