package mybatis.mate.encrypt.mapper;

import mybatis.mate.encrypt.config.SpiceBaseMapper;
import mybatis.mate.encrypt.entity.User;
import mybatis.mate.encrypt.entity.dto.UserDTO;
import mybatis.mate.encrypt.entity.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends SpiceBaseMapper<User> {

    @Update("update user set password=#{u.password},email=#{u.email} where id=#{id}")
    Integer testUpdateById(@Param("id") Long id, @Param("u") User user);

    Integer insertBatchTest(@Param("userList") List<User> userList);

    Integer updateBatchUserById(@Param("userList") List<User> userList);

    List<UserVO> selectUserVOList(@Param("dto") UserDTO dto);

    UserVO selectUserDto(@Param("id") Long id);

    void testNotParams();
}
