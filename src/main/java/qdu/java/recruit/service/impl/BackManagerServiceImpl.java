package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import qdu.java.recruit.common.PageInfo;
import qdu.java.recruit.dao.*;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.mapper.BackManagerMapper;
import qdu.java.recruit.pojo.HrVo;
import qdu.java.recruit.service.BackManagerService;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class BackManagerServiceImpl implements BackManagerService{

    @Autowired
    private BackManagerMapper backManagerMapper;

    private UserMapper userMapper;

    @Autowired @Qualifier("dynamicSqlRepositoryUser")
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private HrMapper hrMapper;

    @Autowired @Qualifier("dynamicSqlRepositoryHr")
    public void setHrMapper(HrMapper hrMapper) {
        this.hrMapper = hrMapper;
    }

    @Override
    public AdminEntity backLogin(Long userid, String password) {
        return backManagerMapper.backLogin(userid,password);
    }

    @Override
    public ArrayList<UserAreaEntity> userArea(){
        return backManagerMapper.userArea();
    }

    @Override
    public ArrayList<CompanyEntity> getAllCompanies() {
        return backManagerMapper.getAllCompanies();
    }

    @Override
    public ArrayList<UserEntity> getAllUsers() {
        return backManagerMapper.getAllUsers();
    }

    @Override
    public WebCountEntity getWebCount(){
        return backManagerMapper.getWebCount();
    }

    @Override
    public int addCompany(String companyName,String companyCode,String description){
        return backManagerMapper.addCompany(companyName,companyCode,description);
    }

    @Override
    public PageInfo<HrVo> getAllHrByCondition(Integer pageNum, Integer pageSize, String mobile) {
        if (mobile.isEmpty()) {
            mobile = null;
        }
        // 查询总人数
        SelectStatementProvider count = select(count())
                .from(HrDynamicSqlSupport.hr)
                .where(HrDynamicSqlSupport.hrmobile, isEqualToWhenPresent(mobile))
                .build().render(RenderingStrategies.MYBATIS3);
        long totalSize = hrMapper.count(count);
        PageHelper.startPage(pageNum, pageSize);
        SelectStatementProvider statementProvider = select(HrMapper.selectHrVoList)
                .from(HrDynamicSqlSupport.hr)
                .leftJoin(DepartmentDynamicSqlSupport.department)
                .on(DepartmentDynamicSqlSupport.departmentid, equalTo(HrDynamicSqlSupport.departmentid))
                .leftJoin(CompanyDynamicSqlSupport.company)
                .on(CompanyDynamicSqlSupport.companyid, equalTo(DepartmentDynamicSqlSupport.companyid))
                .where(HrDynamicSqlSupport.hrmobile, isEqualToWhenPresent(mobile))
                .build().render(RenderingStrategies.MYBATIS3);
        List<HrVo> hrList = hrMapper.selectHrVoMany(statementProvider);
        PageInfo<HrVo> pageInfo = new PageInfo<>();
        pageInfo.setPageData(hrList);
        pageInfo.setTotalSize(totalSize);
        pageInfo.setPageNum(pageNum);
        return pageInfo;
    }

    @Override
    public PageInfo<User> getAllUserByCondition(Integer pageNum, Integer pageSize, Integer stuNum) {
        // 查询总人数
        SelectStatementProvider count = select(count())
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.stunum, isEqualToWhenPresent(stuNum))
                .build().render(RenderingStrategies.MYBATIS3);
        long totalSize = userMapper.count(count);
        PageHelper.startPage(pageNum, pageSize);
        SelectStatementProvider statementProvider = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.stunum, isEqualToWhenPresent(stuNum))
                .orderBy(UserDynamicSqlSupport.stunum.descending())
                .build().render(RenderingStrategies.MYBATIS3);
        List<User> userList = userMapper.selectMany(statementProvider);
        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setPageData(userList);
        pageInfo.setTotalSize(totalSize);
        pageInfo.setPageNum(pageNum);
        return pageInfo;
    }

    @Override
    public int deleteUser(Integer userId, String role) {
        if ("0".equals(role)) {
            return userMapper.deleteByPrimaryKey(userId);
        } else if ("1".equals(role)) {
            return hrMapper.deleteByPrimaryKey(userId);
        } else {
            return userMapper.deleteByPrimaryKey(userId);
        }
    }
}
