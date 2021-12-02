package com.lucian.lucian_blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.tree.NodeTree;

import java.util.List;

public interface CategoryDao extends BaseMapper<Category> {
    List<NodeTree<Category>> getNodeTree(Integer categoryId);
}
