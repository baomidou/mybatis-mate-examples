package mybatis.mate.sharding.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import mybatis.mate.sharding.entity.User;
import mybatis.mate.sharding.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Resource
    private UserMapper mapper;
    final String TEST_USER_NAME = "青苗";

    public boolean test() {

        // 插入 mysql t1 主库
        this.insertT1();

        // mysql t2 从库未做同步，这里是查不出结果的
        Long count = mapper.selectCount(getWrapperByUsername(TEST_USER_NAME));
        if (count > 0) {
            System.err.println("不符合执行预期");
            return false;
        }

        // mysql t2 查初始化 Duo 记录
        System.err.println(mapper.selectCount(getWrapperByUsername("Duo")) == 1);

        // 切换数据源 postgres 查询 Tom 记录
        Long id = mapper.selectByUsername("Tom");
        System.err.println(null != id && id.equals(3L));
        return true;
    }

    @Transactional
    public boolean testRw() {

        // 插入 mysql t1 主库
        this.insertT1();

        // 区别 test() 例子，这里是同一个事务不切换数据源，直接查询 t1 的数据库
        Long count = mapper.selectCount(getWrapperByUsername(TEST_USER_NAME));
        if (count < 1) {
            System.err.println("不符合执行预期");
            return false;
        }

        return true;
    }

    private void insertT1() {
        // 删除历史数据
        mapper.delete(Wrappers.<User>lambdaQuery().eq(User::getUsername, TEST_USER_NAME));
        // 插入 mysql t1 主库
        User user = new User();
        user.setId(IdWorker.getId());
        user.setUsername(TEST_USER_NAME);
        user.setSex(1);
        user.setEmail("jobob@qq.com");
        user.setPassword("123");
        mapper.insert(user);
    }

    protected LambdaQueryWrapper<User> getWrapperByUsername(String username) {
        return Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
    }
}
