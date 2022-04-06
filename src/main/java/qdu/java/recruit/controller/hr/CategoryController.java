package qdu.java.recruit.controller.hr;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.annotation.ResponseBodyResult;
import qdu.java.recruit.dao.CategoryMapper;
import qdu.java.recruit.entity.Category;
import qdu.java.recruit.entity.User;

import java.util.List;

/**
 * @author libuyan
 * @date 2022/4/7 0:27
 */
@RestController
@ResponseBodyResult
@RequestMapping("/category")
public class CategoryController {
    private CategoryMapper categoryMapper;

    @Autowired
    @Qualifier("dynamicSqlRepositoryCategory")
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/getCategoryList")
    public PageInfo<Category> getAllUserByCondition(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageHelper.startPage(1, 10);
        List<Category> select = categoryMapper.select(QueryExpressionDSL::where);
        PageInfo<Category> pageInfo = new PageInfo<>();
        pageInfo.setList(select);
        return pageInfo;
    }
}
