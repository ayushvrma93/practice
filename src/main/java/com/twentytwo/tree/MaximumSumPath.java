package com.twentytwo.tree;

public class MaximumSumPath {

    public int getMaxSum(TreeNode root){

        if(root == null) return 0;
        return root.data + Math.max(getMaxSum(root.left), getMaxSum(root.right));
    }


    public static void main(String[] args) {
        MaximumSumPath maximumSumPath = new MaximumSumPath();
        TreeNode tree1 = Tree.getCompleteBinaryTree();
        TreeNode tree2 = Tree.getPathSum();

        System.out.println(maximumSumPath.getMaxSum(tree1));
        System.out.println(maximumSumPath.getMaxSum(tree2));
    }
}
