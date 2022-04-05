package qdu.java.recruit.pojo;

import lombok.Data;
import qdu.java.recruit.entity.Hr;

/**
 * @author libuyan
 * @date 2022/4/5 21:34
 */
@Data
public class HrVo extends Hr {
    String departmentName;

    String companyName;
}

