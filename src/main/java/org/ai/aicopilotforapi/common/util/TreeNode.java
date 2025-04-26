package org.ai.aicopilotforapi.common.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 通用树节点，支持任意数据类型 T，ID 类型自定义（如 String/Long 等）。
 */
public class TreeNode<T, ID> {
    private ID id;
    private ID parentId;
    private T data;
    private List<TreeNode<T,ID>> children = new ArrayList<>();

    public TreeNode(ID id, ID parentId, T data) {
        this.id = id;
        this.parentId = parentId;
        this.data = data;
    }

    // --- 基本 Getter/Setter ---
    public ID getId() { return id; }
    public void setId(ID id) { this.id = id; }
    public ID getParentId() { return parentId; }
    public void setParentId(ID parentId) { this.parentId = parentId; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public List<TreeNode<T,ID>> getChildren() { return children; }
    public void setChildren(List<TreeNode<T,ID>> children) { this.children = children; }

    // --- 添加/移除子节点 ---
    public void addChild(TreeNode<T,ID> child) {
        this.children.add(child);
    }
    public boolean removeChild(TreeNode<T,ID> child) {
        return this.children.remove(child);
    }
}
