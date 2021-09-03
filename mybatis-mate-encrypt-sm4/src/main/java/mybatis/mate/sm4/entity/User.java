package mybatis.mate.sm4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mybatis.mate.annotation.Algorithm;
import mybatis.mate.annotation.FieldEncrypt;
import mybatis.mate.sm4.config.Sm4Encryptor;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    // 指定加密算法
    @FieldEncrypt(algorithm = Algorithm.PBEWithMD5AndDES)
    private String password;
    // 自定义加密处理器
    @FieldEncrypt(encryptor = Sm4Encryptor.class)
    private String email;
    @FieldEncrypt(algorithm = Algorithm.MD5_32)
    private String md5;

}
