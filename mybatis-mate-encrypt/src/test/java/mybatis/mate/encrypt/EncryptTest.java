package mybatis.mate.encrypt;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import mybatis.mate.encrypt.entity.User;
import mybatis.mate.encrypt.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

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
        user.setId(1L);
        user.setUsername("汤姆凯特");
        user.setPassword("321");
        user.setEmail("tom@163.com");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入汤姆凯特加密 password : " + user.getPassword());
        System.err.println("插入汤姆凯特加密 email : " + user.getEmail());
        user.setId(IdWorker.getId());
        user.setUsername("小羊肖恩");
        user.setPassword("123");
        user.setEmail("jobob@qq.com");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        System.err.println("插入小羊肖恩加密 password : " + user.getPassword());
        System.err.println("插入小羊肖恩加密 email : " + user.getEmail());

        // 测试更新
        this.testUpdate(user);
    }

    @Test
    public void testBatch() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("汤姆凯特");
        user.setPassword("321");
        user.setEmail("tom@163.com");
        userList.add(user);
        User user2 = new User();
        user2.setId(IdWorker.getId());
        user2.setUsername("小羊肖恩");
        user2.setPassword("123");
        user2.setEmail("jobob@qq.com");
        userList.add(user2);

        // 批量插入
        System.err.println("------- 批量 XML 插入 -------");
        mapper.insertBatchTest(userList);
        userList.forEach(System.err::println);

        // 测试更新
        this.testUpdate(user);
    }

    public void testUpdate(User user) {
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
        });
        System.err.println("updateBatchUserById 更新结果：" + mapper.updateBatchUserById(userList));
        System.out.println("加密后的集合: ");
        userList.forEach(System.err::println);
        List<User> newUserList = mapper.selectList(null);
        System.out.println("查询新的结果: ");
        newUserList.forEach(System.err::println);
    }
}
