package mybatis.mate.encrypt.config;

import mybatis.mate.annotation.Algorithm;
import mybatis.mate.encrypt.DefaultEncryptor;
import org.springframework.stereotype.Component;

@Component
public class MyEncryptor extends DefaultEncryptor {

    /**
     * 加密
     *
     * @param algorithm  算法
     * @param password   密码（对称加密算法密钥）
     * @param plaintext  明文
     * @param publicKey  非对称加密算法（公钥）
     * @param metaObject {@link org.apache.ibatis.reflection.MetaObject}
     * @return
     */
    @Override
    public String encrypt(Algorithm algorithm, String password, String publicKey, String plaintext, Object metaObject) throws Exception {
        // 可以判断 plaintext 是否为空加密，为空不加密直接返回 plaintext
        return super.encrypt(algorithm, password, publicKey, plaintext, metaObject);
    }

    /**
     * 解密
     *
     * @param algorithm  算法
     * @param password   密码（对称加密算法密钥）
     * @param encrypt    密文
     * @param privateKey 非对称加密算法（私钥）
     * @param metaObject {@link org.apache.ibatis.reflection.MetaObject}
     * @return
     */
    @Override
    public String decrypt(Algorithm algorithm, String password, String privateKey, String encrypt, Object metaObject) throws Exception {
        // 解密密文 encrypt
        return super.decrypt(algorithm, password, privateKey, encrypt, metaObject);
    }
}
