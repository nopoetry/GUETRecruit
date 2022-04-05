package qdu.java.recruit.pojo;

import lombok.Data;

/**
 * @author libuyan
 * @date 2022/4/5 23:11
 */
@Data
public class UserRoleVo {
    private Integer id;

    private String mobile;

    private String password;

    private String name;

    private String email;

    private String role;
}
