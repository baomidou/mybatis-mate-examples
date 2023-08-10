package mybatis.mate.sharding.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import mybatis.mate.sharding.entity.User;
import mybatis.mate.sharding.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserMapper mapper;

    public void test() {

        // 插入 mysql t1 主库
        User user = new User();
        user.setId(IdWorker.getId());
        user.setUsername("青苗");
        user.setSex(1);
        user.setEmail("jobob@qq.com");
        user.setPassword("123");
        mapper.insert(user);

        // mysql t2 从库未做同步，这里是查不出结果的
        Long count = mapper.selectCount(getWrapperByUsername(user.getUsername()));
        System.err.println(count == 0);

        // mysql t2 查初始化 Duo 记录
        System.err.println(mapper.selectCount(getWrapperByUsername("Duo")) == 1);

        // 切换数据源 postgres 查询 Tom 记录
        Long id = mapper.selectByUsername("Tom");
        System.err.println(null != id && id.equals(3L));
    }

    protected LambdaQueryWrapper<User> getWrapperByUsername(String username) {
        return Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
    }
}
