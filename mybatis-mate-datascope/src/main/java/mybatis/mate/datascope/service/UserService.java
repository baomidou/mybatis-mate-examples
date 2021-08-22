package mybatis.mate.datascope.service;

import mybatis.mate.datascope.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void dataScope() {
        System.err.println("-----publishEvent-----begin");
        userMapper.selectTestList().forEach(System.out::println);
    }
}
