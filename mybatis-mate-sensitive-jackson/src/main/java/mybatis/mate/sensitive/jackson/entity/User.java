package mybatis.mate.sensitive.jackson.entity;

import lombok.Getter;
import lombok.Setter;
import mybatis.mate.annotation.FieldSensitive;
import mybatis.mate.sensitive.jackson.config.SensitiveStrategyConfig;
import mybatis.mate.strategy.SensitiveType;

@Getter
@Setter
public class User {
    private Long id;
    /**
     * 这里是一个自定义的策略 {@link SensitiveStrategyConfig} 初始化注入
     */
    @FieldSensitive(type = "testStrategy")
    private String username;
    /**
     * 默认支持策略 {@link SensitiveType }
     */
    @FieldSensitive(type = SensitiveType.mobile)
    private String mobile;
    @FieldSensitive(type = SensitiveType.email)
    private String email;

}
