package mybatis.mate.encrypt;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import mybatis.mate.encrypt.entity.User;
import mybatis.mate.encrypt.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 字段加解密测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptTest {
    @Resource
    private UserMapper mapper;

    @Test
    @Order(1)
    public void test() {
        User user = new User();
        user.setId(1L);
        user.setUsername("汤姆凯特");
        user.setPassword("321");
        user.setEmail("tom@163.com");
        user.setMd5("123");
        user.setRsa("rsa123");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入汤姆凯特加密 password : " + user.getPassword());
        System.err.println("插入汤姆凯特加密 email : " + user.getEmail());
        user.setId(IdWorker.getId());
        user.setUsername("小羊肖恩");
        user.setPassword("123");
        user.setEmail("jobob@qq.com");
        user.setMd5("567");
        user.setRsa("rsa567");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入小羊肖恩加密 password : " + user.getPassword());
        System.err.println("插入小羊肖恩加密 email : " + user.getEmail());

        // 测试更新
        this.testUpdate(user);
    }

    @Test
    @Order(2)
    public void testBatch() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(2L);
        user.setUsername("汤姆凯特");
        user.setPassword("321");
        user.setEmail("tom@163.com");
        user.setMd5("123");
        user.setRsa("rsa123");
        userList.add(user);
        User user2 = new User();
        user2.setId(IdWorker.getId());
        user2.setUsername("小羊肖恩");
        user2.setPassword("123");
        user2.setEmail("jobob@qq.com");
        user2.setMd5("567");
        user.setRsa("rsa567");
        userList.add(user2);

        // 批量插入
        System.err.println("------- 批量 XML 插入 -------");
        mapper.insertBatchTest(userList);
        userList.forEach(System.err::println);

        // 测试更新
        this.testUpdate(user);
    }

    private void testUpdate(User user) {
        Long userId = user.getId();
        assertThat(userId).isNotNull();

        // 查询保存数据
        user = mapper.selectById(userId);
        System.err.println("解密内容 : " + user);

        // 更新内容
        user.setPassword("567");
        user.setEmail("hi@abc.cn");
        System.err.println("更新结果：" + mapper.updateById(user));
        System.err.println("更新加密 password : " + user.getPassword());
        System.err.println("更新加密 email : " + user.getEmail());
        user = mapper.selectById(userId);
        System.err.println("解密内容 : " + user);

        // Wrapper 更新内容
        user.setPassword("890");
        user.setEmail("890@def.cn");
        user.setMd5("123");
        user.setRsa("rsa123");
        System.err.println("Wrapper 更新结果：" + mapper.update(user, Wrappers.<User>lambdaQuery().eq(User::getId, userId)));
        System.err.println("更新加密 password : " + user.getPassword());
        System.err.println("更新加密 email : " + user.getEmail());
        user = mapper.selectById(userId);
        System.err.println("解密内容 : " + user);

        // 注解 Update 更新内容
        user.setPassword("678");
        user.setEmail("678@dsa.cn");
        System.err.println("注解 Update 更新结果：" + mapper.testUpdateById(userId, user));
        System.err.println("更新加密 password : " + user.getPassword());
        System.err.println("更新加密 email : " + user.getEmail());
        user = mapper.selectById(userId);
        System.err.println("解密内容 : " + user);

        // 批量 XML 更新内容
        List<User> userList = mapper.selectList(null);
        System.err.println("------- 批量 XML 更新内容查询结果 -------");
        userList.forEach(u -> {
            System.err.println(u);
            u.setPassword("678");
            u.setEmail("678@dsa.cn");
            u.setMd5("567");
            u.setRsa("rsa567");
        });
        System.err.println("updateBatchUserById 更新结果：" + mapper.updateBatchUserById(userList));
        System.out.println("加密后的集合: ");
        userList.forEach(System.err::println);
        List<User> newUserList = mapper.selectList(null);
        System.out.println("查询新的结果: ");
        newUserList.forEach(System.err::println);
    }

    @Test
    @Order(3)
    public void testNull() {
        User user = new User();
        user.setId(3L);
        user.setUsername("安吉拉");
        user.setPassword("789");
        user.setMd5("123");
        user.setRsa("rsa123");
        // 测试 null 值问题
        user.setEmail(null);
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入加密后：" + user);
        assertThat(user.getEmail()).isNull();
        user = mapper.selectById(user.getId());
        System.err.println("数据库中的内容：" + user);
        assertThat(user.getEmail()).isNull();
    }

    @Test
    @Order(5)
    public void testWrapper() {
        User user = new User();
        user.setId(3L);
        user.setUsername("安吉拉");
        user.setPassword("789");
        user.setMd5("123");
        user.setRsa("rsa123");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入加密后：" + user);
        assertThat(user.getEmail()).isNull();
        user = mapper.selectById(user.getId());
        System.err.println("数据库中的内容：" + user);
        user.setPassword("123");
        user.setMd5("321");
        user.setRsa("rsa321");
        assertThat(mapper.update(user, Wrappers.<User>query().eq("id", user.getId()))).isEqualTo(1);
        System.err.println("update 加密后：" + user);
        user = mapper.selectById(user.getId());
        System.err.println("数据库中的内容：" + user);
    }

    @Test
    @Order(6)
    public void testRSA() throws Exception {
        Map<String, Key> keyMap = RSA.genKeyPair();
        String publicKey = RSA.getPublicKey(keyMap);
        System.out.println("RSA 公钥：" + publicKey);
        String privateKey = RSA.getPrivateKey(keyMap);
        System.out.println("RSA 私钥：" + privateKey);

        System.err.println("公钥加密——私钥解密");
        String source = "静夜思-床前看月光，疑是地上霜。抬头望山月，低头思故乡。";
        System.out.println("\r加密前文字：\r\n" + source);
        String encodedData = RSA.encryptByPublicKey(source, publicKey);
        System.out.println("加密后文字：\r\n" + encodedData);
        String target = RSA.decryptByPrivateKey(encodedData, privateKey);
        System.out.println("解密后文字: \r\n" + target);

        System.err.println("私钥加密——公钥解密");
        String source2 = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source2);
        String encodedData2 = RSA.encryptByPrivateKey(source2, privateKey);
        System.out.println("加密后：\r\n" + encodedData2);
        String target2 = RSA.decryptByPublicKey(encodedData2, publicKey);
        System.out.println("解密后: \r\n" + target2);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSA.sign(encodedData2.getBytes(), privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSA.verify(encodedData2.getBytes(), publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }
}
