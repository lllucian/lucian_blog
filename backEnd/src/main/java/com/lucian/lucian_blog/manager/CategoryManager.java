package com.lucian.lucian_blog.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.bo.CategoryBO;
import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.translater.Category2BO;
import com.lucian.lucian_blog.bean.translater.Category2SelectVO;
import com.lucian.lucian_blog.bean.translater.CategoryBO2IndexVO;
import com.lucian.lucian_blog.bean.vo.CategoryFormVO;
import com.lucian.lucian_blog.bean.vo.CategoryIndexVO;
import com.lucian.lucian_blog.bean.vo.CategorySelectDataVO;
import com.lucian.lucian_blog.dao.CategoryDao;
import com.lucian.lucian_blog.form_parm.CategoryParam;
import com.lucian.lucian_blog.query_wrapper.CategoryQuery;
import com.lucian.lucian_blog.query_wrapper.CategorySelectQuery;
import com.lucian.lucian_blog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CategoryManager {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Autowired
    Category2BO category2BO;

    @Autowired
    CategoryBO2IndexVO categoryBO2IndexVO;

    @Autowired
    Category2SelectVO category2SelectVO;

    public IPage<CategoryIndexVO> queryList(CategoryQuery categoryQuery) {
        Page<Category> page = new Page<>(categoryQuery.getCurrent(), categoryQuery.getSize());
        Page<Category> categories = categoryDao.selectPage(page, categoryQuery.getQueryWrapper());
        IPage<CategoryBO> categoryBOs = categories.convert(category -> category2BO.translate(category));
        List<CategoryBO> records = categoryBOs.getRecords();
        List<Integer> parentIds = records.stream().map(Category::getParentId).distinct().filter(Objects::nonNull).collect(Collectors.toList());
        if (parentIds.size() != 0) {
            List<Category> parentCategories = categoryDao.selectBatchIds(parentIds);
            Map<Integer, Category> collect = parentCategories.stream().collect(Collectors.toMap(Category::getId, Function.identity(), (key1, key2) -> key2));
            records = records.stream().peek(bo -> {
                Category parentCategory = collect.get(bo.getParentId());
                if (parentCategory != null) bo.setParentCategory(parentCategory);
            }).collect(Collectors.toList());
            categoryBOs.setRecords(records);
        }
        return categoryBOs.convert(bo -> categoryBO2IndexVO.translate(bo));
    }

    /**
     * 分类插入
     * @param categoryParam form 分类数据
     * @return 是否插入成功
     */
    public boolean insert(CategoryParam categoryParam) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryParam, category);
        return categoryService.save(category);
    }

    /**
     * 查找id数据
     * @param id 分类id
     * @return 分类
     */
    public CategoryFormVO findOne(Integer id) {
        if (id == null) return null;
        CategoryFormVO categoryFormVO = new CategoryFormVO();
        Category category = categoryService.getById(id);
        if (category == null) return null;
        BeanUtils.copyProperties(categoryService.getById(id), categoryFormVO);
        return categoryFormVO;
    }

    /**
     * 分类更新
     * @param id 分类id
     * @param categoryParam 要更新的分类信息
     * @return 是否更新成功
     */
    public boolean update(Integer id, CategoryParam categoryParam){
        if (id == null) return false;
        Category category = categoryService.getById(id);
        if (category == null) return false;
        BeanUtils.copyProperties(categoryParam, category);
        return categoryService.updateById(category);
    }

    /**
     * 删除分类
     * @param id 分类id
     * @return 是否删除成功
     */
    public boolean delete(Integer id) {
        if (id == null) return false;
        Category category = categoryService.getById(id);
        if (category == null) return  false;
        return categoryService.removeById(id);
    }

    /**
     * select 检索框
     * @param categorySelectQuery 检索条件
     * @return 符合检索的内容
     */
    public List<CategorySelectDataVO> selectSearch(CategorySelectQuery categorySelectQuery){
        if (categorySelectQuery == null) categorySelectQuery = new CategorySelectQuery();
        List<Category> list = categoryService.list(categorySelectQuery.getQueryWrapper());
        List<CategorySelectDataVO> searchData = new ArrayList<>();
        if (list.size() == 0) return searchData;
        return category2SelectVO.tranCategory2SelectVOList(list);
    }
}
