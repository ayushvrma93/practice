package com.tree;

public class LeftSum {

    private int sumLeftLeaves(TreeNode root){

        if(root == null)
            return 0;

        if(root.left != null && root.left.left == null && root.left.right == null)
            return root.left.data + sumLeftLeaves(root.right);

        return sumLeftLeaves(root.left) + sumLeftLeaves(root.right);
    }


}
