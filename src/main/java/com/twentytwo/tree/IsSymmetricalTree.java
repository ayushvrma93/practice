package com.twentytwo.tree;

public class IsSymmetricalTree {

    public boolean isSymmetricalTree(TreeNode root){
     return isSymmetricalUtil(root.left, root.right);
    }

    private boolean isSymmetricalUtil(TreeNode r1, TreeNode r2){
        if(r1 == null && r2 == null)
            return true;

        if((r1 == null && r2 != null) || (r1 != null && r2 == null))
            return false;

        if(r1.data != r2.data) return false;

        return isSymmetricalUtil(r1.left, r2.right) && isSymmetricalUtil(r1.right, r2.left);
    }

    public static void main(String[] args) {

        TreeNode checkTreeForSymmetry = Tree.getSymmetricalTree();
        TreeNode checkTreeForSymmetry2 = Tree.getNonSymmetricalTree();

        IsSymmetricalTree isSymmetricalTree = new IsSymmetricalTree();

        System.out.println(isSymmetricalTree.isSymmetricalTree(checkTreeForSymmetry));
        System.out.println(isSymmetricalTree.isSymmetricalTree(checkTreeForSymmetry2));
    }

}
