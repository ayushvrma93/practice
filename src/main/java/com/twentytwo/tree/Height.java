package com.twentytwo.tree;

public class Height {

    public int getHeight(TreeNode root){

        if(root == null)
            return 0;

        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        return Math.max(lHeight, rHeight) + 1;
    }
}
