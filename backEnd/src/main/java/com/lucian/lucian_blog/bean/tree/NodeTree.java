package com.lucian.lucian_blog.bean.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NodeTree<T> {
    private T current;

    private List<NodeTree<T>> nextArray;
}
