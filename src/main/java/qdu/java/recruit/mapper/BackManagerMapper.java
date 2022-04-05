package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.*;

import java.util.ArrayList;

@Mapper
public interface BackManagerMapper {
    @Select("SELECT * FROM `admin` WHERE userid = #{userid} AND password = md5(#{password})")
    AdminEntity backLogin(@Param("userid") Long userid, @Param("password") String password);

    @Select("SELECT COUNT(*) AS 'usernum',`province` AS 'area' FROM `user` GROUP BY `province`")
    ArrayList<UserAreaEntity> userArea();

    @Select("SELECT * FROM `company`")
    ArrayList<CompanyEntity> getAllCompanies();

    @Select("SELECT `userId`,`name`,`nickname`,`mobile`,`email`,`city`,`eduDegree`,`graduation` FROM `user`")
    ArrayList<UserEntity> getAllUsers();

    @Select("SELECT COUNT(companyId) AS `companynum`,(SELECT COUNT(applicationId) FROM `application`) AS `offernum`, (SELECT COUNT(userId) FROM `user`) AS `usernum`, (SELECT max(hits) FROM `position`) AS `visitnum` FROM `company`")
    WebCountEntity getWebCount();

    @Insert("INSERT INTO `company`(`companyLogo`,`state`,`companyName`,`companyCode`,`description`) VALUES (7,1,#{companyName},#{companyCode},#{description})")
    Integer addCompany(@Param("companyName") String companyName,@Param("companyCode") String companyCode,@Param("description") String description);
}
