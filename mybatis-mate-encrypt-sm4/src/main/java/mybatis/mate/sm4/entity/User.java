package mybatis.mate.sm4.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import mybatis.mate.annotation.Algorithm;
import mybatis.mate.annotation.FieldBind;
import mybatis.mate.annotation.FieldEncrypt;
import mybatis.mate.encrypt.SM4;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 加密数据绑定混合
     */
    @FieldEncrypt(algorithm = Algorithm.MD5_32)
    @FieldBind(type = "test_bind", target = "md5Text")
    private String md5;

    @TableField(exist = false)
    private String md5Text;

}
