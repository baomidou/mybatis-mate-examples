package mybatis.mate.sm4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mybatis.mate.annotation.Algorithm;
import mybatis.mate.annotation.FieldEncrypt;
import mybatis.mate.encrypt.SM4;

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
    /**
     * 注意 SM4 加密算法必须依赖 bouncycastle 库，
     * 另外密钥 key 必须使用这个方法生成 {@link SM4#generateKey()}
     */
    @FieldEncrypt(algorithm = Algorithm.SM4)
    private String email;
    @FieldEncrypt(algorithm = Algorithm.MD5_32)
    private String md5;

}
