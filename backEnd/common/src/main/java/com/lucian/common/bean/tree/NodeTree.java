package com.lucian.common.bean.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Getter
@Setter
public class NodeTree<T> {
    private Integer id;

    private T current;

    private List<NodeTree<T>> nextArray;
}
