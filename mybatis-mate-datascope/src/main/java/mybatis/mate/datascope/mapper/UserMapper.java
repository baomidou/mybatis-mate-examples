package mybatis.mate.datascope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import mybatis.mate.annotation.DataColumn;
import mybatis.mate.annotation.DataScope;
import mybatis.mate.datascope.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 这里作用于整个类 @DataScope(...)
@Mapper
public interface UserMapper extends BaseMapper<User> {

    // 测试 test 类型数据权限范围，混合分页模式
    @DataScope(type = "test", value = {
            // 关联表 user 别名 u 指定部门字段权限
            @DataColumn(alias = "u", name = "department_id"),
            // 关联表 user 别名 u 指定手机号字段（自己判断处理）
            @DataColumn(alias = "u", name = "mobile")
    })
    @Select("select u.* from user u")
    List<User> selectTestList(IPage<User> page, Long id, @Param("name") String username);

    // 忽略某个方法
    // @DataScope(ignore = true)
}
