package mybatis.mate.encrypt;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import mybatis.mate.encrypt.entity.User;
import mybatis.mate.encrypt.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
    public void test() {
        User user = new User();
        user.setId(IdWorker.getId());
        user.setUsername("小羊肖恩");
        user.setPassword("123");
        user.setEmail("jobob@qq.com");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入加密 password : " + user.getPassword());
        System.err.println("插入加密 email : " + user.getEmail());

        Long userId = user.getId();
        assertThat(userId).isNotNull();

        // 查询保存数据
        user = mapper.selectById(userId);
        System.err.println("解密内容 : " + user);

        // 更新内容
        user.setPassword("567");
        user.setEmail("hi@abc.cn");
        System.err.println("更新结果：" + mapper.updateById(user));;
        System.err.println("更新加密 password : " + user.getPassword());
        System.err.println("更新加密 email : " + user.getEmail());
        user = mapper.selectById(userId);
        System.err.println("解密内容 : " + user);
    }
}
