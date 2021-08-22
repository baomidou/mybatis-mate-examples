package mybatis.mate.datascope.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private Long departmentId;
    private String username;
    private String mobile;

}
