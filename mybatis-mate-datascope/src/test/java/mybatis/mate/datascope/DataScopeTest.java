package mybatis.mate.datascope;

import mybatis.mate.datascope.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 数据权限测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataScopeTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        // 查看控制台，输出执行 sql 语句
        userService.dataScope();
    }

    @Test
    public void testDefault() {
        // default 方法无法拦截，可以通过重写父类方法间接解决
        userService.dataScopeDefaultMethod();
    }
}
