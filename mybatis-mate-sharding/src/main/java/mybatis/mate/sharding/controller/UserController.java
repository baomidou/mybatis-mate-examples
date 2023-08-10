package mybatis.mate.sharding.controller;

import lombok.AllArgsConstructor;
import mybatis.mate.ddl.DdlScript;
import mybatis.mate.sharding.ShardingKey;
import mybatis.mate.sharding.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;

@RestController
@AllArgsConstructor
public class UserController {
    private DdlScript ddlScript;
    private UserService userService;

    // 测试访问 http://localhost:8080/test
    @GetMapping("/test")
    public boolean testSharding() throws Exception {
        this.initMysqlT2();
        userService.test();
        return true;
    }

    public void initMysqlT2() throws Exception {
        // 切换到 mysql 从库，执行 SQL 脚本
        ShardingKey.change("mysqlt2");
        ddlScript.run(new StringReader("DELETE FROM user;\n" +
                "INSERT INTO user (id, username, password, sex, email) VALUES\n" +
                "(20, 'Duo', '123456', 0, 'Duo@baomidou.com');"));
    }
}
