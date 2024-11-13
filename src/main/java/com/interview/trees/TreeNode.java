package com.interview.trees;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;

    public int val;

    public TreeNode(int val) {
        this(null, null, val);
    }

    public TreeNode(TreeNode left, TreeNode right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }
}
