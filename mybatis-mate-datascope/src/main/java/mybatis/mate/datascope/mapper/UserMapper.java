package mybatis.mate.datascope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mybatis.mate.annotation.DataScope;
import mybatis.mate.datascope.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @DataScope
    @Select("select * from user where mobile like '153%'")
    List<User> selectTestList();
}
