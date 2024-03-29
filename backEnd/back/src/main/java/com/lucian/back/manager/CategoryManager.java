package com.lucian.back.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.bo.CategoryBO;
import com.lucian.back.bean.translate.Category2BO;
import com.lucian.back.bean.translate.Category2SelectVO;
import com.lucian.back.bean.translate.CategoryBO2IndexVO;
import com.lucian.back.bean.vo.CategoryFormVO;
import com.lucian.back.bean.vo.CategoryIndexVO;
import com.lucian.back.bean.vo.CategorySelectDataVO;
import com.lucian.back.dao.CategoryDao;
import com.lucian.back.form_parm.CategoryParam;
import com.lucian.back.query_wrapper.CategoryQuery;
import com.lucian.back.query_wrapper.CategorySelectQuery;
import com.lucian.back.service.CategoryService;
import com.lucian.common.bean.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
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
        List<String> parentIds = records.stream().map(Category::getParentId).distinct().filter(Objects::nonNull).collect(Collectors.toList());
        if (parentIds.size() != 0) {
            List<Category> parentCategories = categoryDao.selectBatchIds(parentIds);
            Map<String, Category> collect = parentCategories.stream().collect(Collectors.toMap(Category::getId, Function.identity(), (key1, key2) -> key2));
            records = records.stream().peek(bo -> {
                Category parentCategory = collect.get(bo.getParentId());
                if (parentCategory != null) {
                    bo.setParentCategory(parentCategory);
                }
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
    public CategoryFormVO findOne(String id) {
        if (id == null) {
            return null;
        }
        CategoryFormVO categoryFormVO = new CategoryFormVO();
        Category category = categoryService.getById(id);
        if (category == null) {
            return null;
        }
        BeanUtils.copyProperties(categoryService.getById(id), categoryFormVO);
        return categoryFormVO;
    }

    /**
     * 分类更新
     * @param id 分类id
     * @param categoryParam 要更新的分类信息
     * @return 是否更新成功
     */
    public boolean update(String id, CategoryParam categoryParam){
        if (id == null) {
            return false;
        }
        Category category = categoryService.getById(id);
        if (category == null) {
            return false;
        }
        BeanUtils.copyProperties(categoryParam, category);
        return categoryService.updateById(category);
    }

    /**
     * 删除分类
     * @param id 分类id
     * @return 是否删除成功
     */
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        Category category = categoryService.getById(id);
        if (category == null) {
            return  false;
        }
        return categoryService.removeById(id);
    }

    /**
     * select 检索框
     * @param categorySelectQuery 检索条件
     * @return 符合检索的内容
     */
    public List<CategorySelectDataVO> selectSearch(CategorySelectQuery categorySelectQuery){
        if (categorySelectQuery == null) {
            categorySelectQuery = new CategorySelectQuery();
        }
        List<Category> list = categoryService.list(categorySelectQuery.getQueryWrapper());
        List<CategorySelectDataVO> searchData = new ArrayList<>();
        if (list.size() == 0) {
            return searchData;
        }
        return category2SelectVO.tranCategory2SelectVOList(list);
    }

    /**
     *  获取可以关联的categoryId
     * @param categoryId 分类id
     * @return 可以关联的categoryId集合
     */
//    public List<CategorySelectDataVO> parentCategory(Integer categoryId){
//        if (categoryId == null) {
//            List<Category> list = categoryService.list();
//            return category2SelectVO.tranCategory2SelectVOList(list);
//        }
//        List<NodeTree<Category>> nodeTree = categoryDao.getNodeTree(categoryId);
//        if(nodeTree == null) return null;
//        List<Category> categories = new ArrayList<>();
//        categories = canSelectData(categories, nodeTree);
//        return category2SelectVO.tranCategory2SelectVOList(categories);
//    }
//
//    public List<Category> canSelectData(List<Category> categories, List<NodeTree<Category>> nodeTree){
//        nodeTree.forEach(node -> {
//            categories.add(node.getCurrent());
//            if (node.getNextArray() != null) canSelectData(categories, node.getNextArray());
//        });
//        return categories;
//    }
    public List<CategorySelectDataVO> parentCategory(String categoryId){
        List<Category> list = categoryService.list();
        if (list == null || list.size() == 0) {
            return null;
        }
        list = list.stream().sorted(Comparator.comparing(Category::getSort).thenComparing(Comparator.comparing(Category::getId).reversed())).collect(Collectors.toList());
        // 如果不存在分类id则查处全部的分类id并返回
        if (categoryId == null) {
            return category2SelectVO.tranCategory2SelectVOList(list);
        }
        return category2SelectVO.tranCategory2SelectVOList(getNodeData(list, categoryId, "getId"));
    }

    /**
     * 移除不能选择的分类
     * @param list 分类集合
     * @param categoryId 要移除的分类id
     * @param methodName 是本身的id或者parentId
     * @return 移除后的分类
     */
    public List<Category> getNodeData(List<Category> list, String categoryId, String methodName){
        // 先找有没有这个分类id
        List<Category> parentCategory = list.stream().filter(category -> {
            try {
                Method method = Class.forName("com.lucian.lucian_blog.bean.entity.Category").getMethod(methodName);
                return Objects.equals(method.invoke(category), categoryId);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
        if (parentCategory.size() == 0) {
            return list;
        }
        list.removeAll(parentCategory);
        if (list.size() == 0) {
            return null;
        }
        return getNodeData(list, parentCategory.get(0).getId(), "getParentId");
    }

    /**
     * 获取options
     * @param ids 分类id
     * @return options
     */
    public List<CategorySelectDataVO> getSelectData(List<String> ids){
        if (ids == null || ids.size() == 0) {
            return null;
        }
        return category2SelectVO.tranCategory2SelectVOList(categoryService.listByIds(ids));
    }
}
