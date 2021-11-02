package com.lucian.lucian_blog.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.bo.CategoryBO;
import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.translater.Category2BO;
import com.lucian.lucian_blog.bean.translater.CategoryBO2IndexVO;
import com.lucian.lucian_blog.bean.vo.CategoryIndexVO;
import com.lucian.lucian_blog.dao.CategoryDao;
import com.lucian.lucian_blog.query_wrapper.CategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CategoryManager {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private Category2BO category2BO;

    @Autowired
    private CategoryBO2IndexVO categoryBO2IndexVO;

    public IPage<CategoryIndexVO> queryList(CategoryQuery categoryQuery) {
        Page<Category> page = new Page<>(categoryQuery.getCurrentPage(), categoryQuery.getLimit());
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
}
