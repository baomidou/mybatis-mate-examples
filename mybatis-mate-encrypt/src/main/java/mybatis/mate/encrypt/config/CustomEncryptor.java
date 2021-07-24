package mybatis.mate.encrypt.config;

import mybatis.mate.annotation.Algorithm;
import mybatis.mate.encrypt.IEncryptor;

// 自定义加密算法，这里为开启使用默认加密库
//@Component
public class CustomEncryptor implements IEncryptor {

    @Override
    public String encrypt(Algorithm algorithm, String password, String src) {
        return "加密返回";
    }

    @Override
    public String decrypt(Algorithm algorithm, String password, String encrypt) {
        return "解密返回";
    }
}
