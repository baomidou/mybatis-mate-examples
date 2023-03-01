package mybatis.mate.sensitive.jackson.controller;

import jakarta.servlet.http.HttpServletRequest;
import mybatis.mate.databind.ISensitiveStrategy;
import mybatis.mate.databind.RequestDataTransfer;
import mybatis.mate.sensitive.jackson.entity.User;
import mybatis.mate.sensitive.jackson.mapper.UserMapper;
import mybatis.mate.strategy.SensitiveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ISensitiveStrategy sensitiveStrategy;

    // 测试访问 http://localhost:8080/info
    @GetMapping("/info")
    public User info() {
        return userMapper.selectById(1L);
    }

    // 测试返回 map 访问 http://localhost:8080/map
    @GetMapping("/map")
    public Map<String, Object> map() {
        // 测试嵌套对象脱敏
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", userMapper.selectById(1L));
        userMap.put("test", 123);
        userMap.put("userMap", new HashMap<String, Object>() {{
            put("user2", userMapper.selectById(2L));
            put("test2", "hi china");
        }});
        // 手动调用策略脱敏
        userMap.put("mobile", sensitiveStrategy.getStrategyFunctionMap()
                .get(SensitiveType.mobile).apply("15315388888"));
        return userMap;
    }

    // 测试访问 http://localhost:8080/list
    // 全部不脱敏 http://localhost:8080/list?skip=1
    // 指定属性不脱敏 http://localhost:8080/list?skip=2
    @GetMapping("/list")
    public List<User> list(HttpServletRequest request) {
        String skip = request.getParameter("skip");
        if (Objects.equals("1", skip)) {
            // 跳过脱密处理
            RequestDataTransfer.skipSensitive();
        } else if (null != skip) {
            // 忽略 username 及 email 属性
            RequestDataTransfer.skipSensitive("username;email");
        }
        return userMapper.selectList(null);
    }
}
