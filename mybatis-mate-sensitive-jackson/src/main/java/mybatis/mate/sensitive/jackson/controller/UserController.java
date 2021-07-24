package mybatis.mate.sensitive.jackson.controller;

import mybatis.mate.sensitive.jackson.entity.User;
import mybatis.mate.sensitive.jackson.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    // 测试访问 http://localhost:8080/info
    @GetMapping("/info")
    public User info() {
        return userMapper.selectById(1L);
    }

    // 测试访问 http://localhost:8080/list
    @GetMapping("/list")
    public List<User> list() {
        return userMapper.selectList(null);
    }
}
