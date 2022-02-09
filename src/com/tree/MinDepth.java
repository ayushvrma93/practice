package com.tree;

public class MinDepth {

    public int minDepth(TreeNode root){

        if(root == null)
            return 0;

        if(root.left == null && root.right == null)
            return 1;

        if(root.left == null)
            return minDepth(root.right) + 1;

        if(root.right == null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public static void main(String[] args) {

        MinDepth minDepth = new MinDepth();
        TreeNode tree1 = Tree.getHeightBalancedTree();

        System.out.println(minDepth.minDepth(tree1));
    }
}
