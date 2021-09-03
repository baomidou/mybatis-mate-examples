package mybatis.mate.sm4;

import mybatis.mate.encrypt.SM4;
import mybatis.mate.sm4.entity.User;
import mybatis.mate.sm4.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 字段加解密测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptTest {
    @Resource
    private UserMapper mapper;

    @Test
    public void testSm4() {
        User user = new User(1L, "hi china", "123456", "asd@qq.com", "asd");
        Assertions.assertEquals(1, mapper.insert(user));
        System.out.println("加密内容：" + user);
        System.out.println("查询数据库内容：" + mapper.selectById(user.getId()));
    }

    @Test
    public void encrypt() throws Exception {
        final String key = SM4.generateKey();
        System.out.println("加密 KEY = " + key);
        String output = SM4.encrypt(key, "sm4对称加密<pkcs5>");
        System.out.printf("SM4-ECB-PKCS5Padding，加密输出HEX = %s \n", output);
        System.out.printf("SM4-ECB-PKCS5Padding，解密输出 = %s \n", SM4.decrypt(key, output));
    }
}
