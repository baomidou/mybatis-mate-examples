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
        assertThat(user.getId()).isNotNull();
        System.err.println(mapper.selectById(user.getId()));
    }
}
