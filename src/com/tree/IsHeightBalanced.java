package com.tree;

public class IsHeightBalanced {

    public boolean ByNSquaredComplexity(TreeNode root){

        if(root == null)
            return true;

        int lheight = height(root.left);
        int rheight = height(root.right);

        if(Math.abs(lheight - rheight) > 1)
            return false;

        return ByNSquaredComplexity(root.left) && ByNSquaredComplexity(root.right);
    }


    private int height(TreeNode root){

        if(root == null)
            return 0;

        int lheight = height(root.left);
        int rheight = height(root.right);

        return Math.max(lheight, rheight) + 1;
    }

    private static class Height{
        int height;
    }

    public boolean byN(TreeNode root, Height height){

        if(root == null) return true;

        Height lHeight = new Height();
        Height rHeight = new Height();

        boolean l = byN(root.left, lHeight);
        boolean r = byN(root.right, rHeight);

        height.height = Math.max(lHeight.height, rHeight.height) + 1;

        if(Math.abs(lHeight.height - rHeight.height) > 1)
            return false;

        return l && r;
    }

    public static void main(String[] args) {

        TreeNode tree1 = Tree.getHeightBalancedTree();
        TreeNode tree2 = Tree.getNotHeightBalancedTree();
        IsHeightBalanced isHeightBalanced = new IsHeightBalanced();

        System.out.println(isHeightBalanced.ByNSquaredComplexity(tree1));
        System.out.println(isHeightBalanced.ByNSquaredComplexity(tree2));

        System.out.println(isHeightBalanced.byN(tree1, new Height()));
        System.out.println(isHeightBalanced.byN(tree2, new Height()));

    }
}
