package com.interview.trees;

public class Driver {
    public static void main(String[] args) {
        Traversal traversal = new Traversal();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println(traversal.inorder(root));
        System.out.println(traversal.inorderWithStack(root));

        System.out.println("-----------------------------------------");
        System.out.println("Preorder Traversal");
        System.out.println(traversal.preorder(root));
        System.out.println(traversal.preorderWithStack(root));

        System.out.println("-----------------------------------------");
        System.out.println("Postorder Traversal");
        System.out.println(traversal.postorder(root));
    }
}
