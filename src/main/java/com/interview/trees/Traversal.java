package com.interview.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversal {

    public List<Integer> postorder(TreeNode root) {
        List<Integer> postorderList = new ArrayList<>();
        postorderHelper(root, postorderList);
        return postorderList;
    }

    private void postorderHelper(TreeNode root, List<Integer> postorderList) {
        if (root == null) return;
        postorderHelper(root.left, postorderList);
        postorderHelper(root.right, postorderList);
        postorderList.add(root.val);
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        inorderHelper(root, inorderList);
        return inorderList;
    }

    public List<Integer> inorderWithStack(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> inorderList = new ArrayList<>();

        while (root != null || !st.isEmpty()) {

            while (root != null) {
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            inorderList.add(root.val);
            root = root.right;
        }

        return inorderList;
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> preorderList = new ArrayList<>();
        preporderHelper(root, preorderList);
        return preorderList;
    }

    private void preporderHelper(TreeNode root, List<Integer> preorderList) {
        if (root == null) return;
        preorderList.add(root.val);
        preporderHelper(root.left, preorderList);
        preporderHelper(root.right, preorderList);
    }

    public List<Integer> preorderWithStack(TreeNode root) {
        List<Integer> preorderList = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            preorderList.add(node.val);

            if (node.right != null) st.push(node.right);
            if (node.left != null) st.push(node.left);
        }

        return preorderList;
    }

    private void inorderHelper(TreeNode root, List<Integer> inorderList) {
        if (root == null) return;
        inorderHelper(root.left, inorderList);
        inorderList.add(root.val);
        inorderHelper(root.right, inorderList);
    }
}
