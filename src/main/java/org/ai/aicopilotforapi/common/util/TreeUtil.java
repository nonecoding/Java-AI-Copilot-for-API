package org.ai.aicopilotforapi.common.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TreeUtil {

    /**
     * 将一维节点列表构建成多叉树。若有多个根(id.parentId==null)，返回多根列表。
     *
     * @param nodes 所有节点列表
     * @param <T>   数据类型
     * @param <ID>  ID 类型
     * @return 根节点列表
     */
    public static <T, ID> List<TreeNode<T,ID>> buildTree(List<TreeNode<T,ID>> nodes) {
        Map<ID, TreeNode<T,ID>> map = nodes.stream()
            .collect(Collectors.toMap(TreeNode::getId, Function.identity()));
        List<TreeNode<T,ID>> roots = new ArrayList<>();
        for (TreeNode<T,ID> node : nodes) {
            ID pid = node.getParentId();
            if (pid == null || !map.containsKey(pid)) {
                roots.add(node);
            } else {
                map.get(pid).addChild(node);
            }
        }
        return roots;
    }

    /**
     * 在树中查找节点（先序），返回第一个匹配 id 的节点，找不到返回 null。
     */
    public static <T, ID> TreeNode<T,ID> findNodeById(TreeNode<T,ID> root, ID id) {
        if (root.getId().equals(id)) return root;
        for (TreeNode<T,ID> child : root.getChildren()) {
            TreeNode<T,ID> res = findNodeById(child, id);
            if (res != null) return res;
        }
        return null;
    }

    /**
     * 按条件查找节点，返回第一个满足 predicate 的节点。
     */
    public static <T, ID> TreeNode<T,ID> findNode(TreeNode<T,ID> root, Predicate<T> predicate) {
        if (predicate.test(root.getData())) return root;
        for (TreeNode<T,ID> child : root.getChildren()) {
            TreeNode<T,ID> res = findNode(child, predicate);
            if (res != null) return res;
        }
        return null;
    }

    /**
     * 添加新节点到 parent 节点下。
     */
    public static <T, ID> void addNode(TreeNode<T,ID> parent, TreeNode<T,ID> newNode) {
        parent.addChild(newNode);
    }

    /**
     * 在树中删除指定 id 的节点（及其子树）。如果删除根节点，需调用者自行处理。
     * 返回是否删除成功。
     */
    public static <T, ID> boolean deleteNode(TreeNode<T,ID> root, ID id) {
        Iterator<TreeNode<T,ID>> it = root.getChildren().iterator();
        while (it.hasNext()) {
            TreeNode<T,ID> child = it.next();
            if (child.getId().equals(id)) {
                it.remove();
                return true;
            } else {
                boolean removed = deleteNode(child, id);
                if (removed) return true;
            }
        }
        return false;
    }

    /**
     * 先序遍历，访问顺序：根 -> 子树（从左到右）
     */
    public static <T, ID> void preOrder(TreeNode<T,ID> root, java.util.function.Consumer<TreeNode<T,ID>> visitor) {
        visitor.accept(root);
        for (TreeNode<T,ID> child : root.getChildren()) {
            preOrder(child, visitor);
        }
    }

    /**
     * 后序遍历，访问顺序：子树 -> 根
     */
    public static <T, ID> void postOrder(TreeNode<T,ID> root, java.util.function.Consumer<TreeNode<T,ID>> visitor) {
        for (TreeNode<T,ID> child : root.getChildren()) {
            postOrder(child, visitor);
        }
        visitor.accept(root);
    }

    /**
     * 层序遍历（广度优先）
     */
    public static <T, ID> void levelOrder(TreeNode<T,ID> root, java.util.function.Consumer<TreeNode<T,ID>> visitor) {
        Queue<TreeNode<T,ID>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T,ID> node = queue.poll();
            visitor.accept(node);
            queue.addAll(node.getChildren());
        }
    }

    /**
     * 扁平化整棵树为列表（先序）。
     */
    public static <T, ID> List<TreeNode<T,ID>> flatten(TreeNode<T,ID> root) {
        List<TreeNode<T,ID>> list = new ArrayList<>();
        preOrder(root, list::add);
        return list;
    }

    /**
     * map：将树中的数据 T 变换为 R，结构不变。
     */
    public static <T, R, ID> TreeNode<R,ID> map(TreeNode<T,ID> root, Function<T,R> mapper) {
        TreeNode<R,ID> newRoot = new TreeNode<>(root.getId(), root.getParentId(), mapper.apply(root.getData()));
        for (TreeNode<T,ID> child : root.getChildren()) {
            newRoot.addChild(map(child, mapper));
        }
        return newRoot;
    }

    /**
     * filter：保留满足 predicate 的节点及其符合条件的子树，返回新的子树根。
     * 不满足条件的节点会被剪掉。
     */
    public static <T, ID> TreeNode<T,ID> filter(TreeNode<T,ID> root, Predicate<T> predicate) {
        if (!predicate.test(root.getData())) return null;
        TreeNode<T,ID> newRoot = new TreeNode<>(root.getId(), root.getParentId(), root.getData());
        for (TreeNode<T,ID> child : root.getChildren()) {
            TreeNode<T,ID> sub = filter(child, predicate);
            if (sub != null) newRoot.addChild(sub);
        }
        return newRoot;
    }

    /**
     * 计算树的深度（最大层级数）。
     */
    public static <T, ID> int depth(TreeNode<T,ID> root) {
        if (root.getChildren().isEmpty()) return 1;
        int max = 0;
        for (TreeNode<T,ID> c : root.getChildren()) {
            max = Math.max(max, depth(c));
        }
        return max + 1;
    }

    /**
     * 统计节点总数。
     */
    public static <T, ID> int count(TreeNode<T,ID> root) {
        return flatten(root).size();
    }
}
