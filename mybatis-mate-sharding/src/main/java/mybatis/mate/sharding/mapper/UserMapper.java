package mybatis.mate.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mybatis.mate.annotation.Sharding;
import mybatis.mate.sharding.config.MyShardingStrategy;
import mybatis.mate.sharding.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
//@Sharding("mysql")
public interface UserMapper extends BaseMapper<User> {

    @Sharding(value = "postgres", strategy = MyShardingStrategy.class)
    @Select("select id from \"public\".\"user\" where username=#{username}")
    Long selectByUsername(String username);

}
