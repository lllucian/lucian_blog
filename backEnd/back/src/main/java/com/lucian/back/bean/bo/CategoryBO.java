package com.lucian.back.bean.bo;

import com.lucian.common.bean.entity.Category;
import com.lucian.common.bean.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryBO extends Category {
    /**
     * 父分类信息
     */
    private Category parentCategory;
    /**
     * 分类下面的信息
     */
    private List<Post> postList;
}
