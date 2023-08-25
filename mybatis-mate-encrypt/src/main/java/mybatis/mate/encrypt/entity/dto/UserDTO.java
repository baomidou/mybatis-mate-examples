package mybatis.mate.encrypt.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    // 文档 https://baomidou.com/pages/fd41d8/ 如果是 MP 内容方法查询可以使用以下注解，返回结果需要实体 @TableName(autoResultMap = true) 注解打开映射
    // @TableField(typeHandler = RSATypeHandler.class)
    private String email;

}
