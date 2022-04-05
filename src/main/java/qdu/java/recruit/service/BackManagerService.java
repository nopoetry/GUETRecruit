package qdu.java.recruit.service;

import qdu.java.recruit.common.PageInfo;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.pojo.HrVo;

import java.util.ArrayList;

public interface BackManagerService {
    AdminEntity backLogin(Long userid, String password);

    ArrayList<UserAreaEntity> userArea();

    ArrayList<CompanyEntity> getAllCompanies();

    ArrayList<UserEntity> getAllUsers();

    WebCountEntity getWebCount();

    int addCompany(String companyName, String companyCode, String description);

    PageInfo<User> getAllUserByCondition(Integer pageNum, Integer pageSize, Integer stuNum);

    PageInfo<HrVo> getAllHrByCondition(Integer pageNum, Integer pageSize, String mobile);

    int deleteUser(Integer userId, String role);
}
