package qdu.java.recruit.controller.admin;

import com.google.gson.Gson;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.common.PageInfo;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.pojo.HrVo;
import qdu.java.recruit.service.BackManagerService;
import sun.security.provider.MD5;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/manager")
public class BackManagerController {

    @Autowired
    private BackManagerService backManagerService;

    @RequestMapping("/login")
    public String init() {
        return "manager/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "manager/index";
    }

    @RequestMapping("/index_v3")
    public String index_v3() {
        return "manager/index_v3";
    }

    @RequestMapping("/nestable_list")
    public String teams_board() {
        return "manager/nestable_list";
    }

    @RequestMapping("/contacts")
    public String contacts() {
        return "manager/contacts";
    }

    @RequestMapping("/table_jqgrid")
    public String table() {
        return "manager/table_jqgrid";
    }


    @RequestMapping("/widgets")
    public String widgets() {
        return "manager/widgets";
    }

    @RequestMapping("/adminlogin")
    @ResponseBody
    public AdminEntity login(Long username, String password) {
        return backManagerService.backLogin(username, password);
    }

    @RequestMapping("/addcompany")
    @ResponseBody
    public Map<String, Object> addcompany(String companyName, String companyCode, String description) {
        Map<String, Object> map = new HashMap<>();
        int result = backManagerService.addCompany(companyName, companyCode, description);
        if (result == 0) {
            map.put("state", "0");
        } else {
            map.put("state", "1");
        }
        return map;
    }


    @RequestMapping(value = "/userareachart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> area() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<UserAreaEntity> area = backManagerService.userArea();
        UserAreaEntity userAreaEntity;
        for (int i = 0; i < area.size(); i++) {
            userAreaEntity = area.get(i);
            map.put(userAreaEntity.getArea(), userAreaEntity.getUsernum());
        }
        return map;
    }

    @RequestMapping("webcount")
    @ResponseBody
    public Map<String, Object> webcount() {
        Map<String, Object> map = new HashMap<>();
        WebCountEntity webCountEntity = backManagerService.getWebCount();
        map.put("companynum", webCountEntity.getCompanynum());
        map.put("offernum", webCountEntity.getOffernum());
        map.put("usernum", webCountEntity.getUsernum());
        map.put("visitnum", webCountEntity.getVisitnum());
        System.out.println(map);
        return map;
    }

    @GetMapping("getAllCompany")
    @ResponseBody
    public List<CompanyEntity> getAllCompany() {
        return backManagerService.getAllCompanies();
    }


    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UserEntity> getUser() {
        return backManagerService.getAllUsers();
    }

    @GetMapping("getAllUserByCondition")
    @ResponseBody
    public PageInfo<User> getAllUserByCondition(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("stuNum") Integer stuNum) {
        return backManagerService.getAllUserByCondition(pageNum, pageSize, stuNum);
    }

    @GetMapping("getAllHrByCondition")
    @ResponseBody
    public PageInfo<HrVo> getAllHrByCondition(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("mobile") String mobile) {
        return backManagerService.getAllHrByCondition(pageNum, pageSize, mobile);
    }

    @DeleteMapping
    @ResponseBody
    public Map<String, Object> deleteUserByUserId(@RequestParam("userId") Integer userId, @RequestParam("role") String role) {
        Map<String, Object> map = new HashMap<>();
        int result = 0;
        result = backManagerService.deleteUser(userId, role);
        map.put("state", result);
        return map;
    }

}
