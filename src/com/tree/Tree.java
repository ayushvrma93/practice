package com.tree;

public class Tree {

    public static TreeNode getBST(){

        TreeNode bstRoot = new TreeNode(7);

        bstRoot.left = new TreeNode(2);
        bstRoot.left.right = new TreeNode(6);
        bstRoot.right = new TreeNode(20);

        bstRoot.right.left = new TreeNode(15);
        bstRoot.right.right = new TreeNode(70);

        return bstRoot;
    }


    public static TreeNode getNonSymmetricalTree(){

        TreeNode nonSymmetricalTree = new TreeNode(7);

        nonSymmetricalTree.left = new TreeNode(2);
        nonSymmetricalTree.left.right = new TreeNode(6);

        nonSymmetricalTree.right = new TreeNode(2);

        nonSymmetricalTree.right.left = new TreeNode(6);
        nonSymmetricalTree.right.right = new TreeNode(70);

        return nonSymmetricalTree;
    }

    public static TreeNode getSymmetricalTree(){

        TreeNode symmetricalTree = new TreeNode(7);

        symmetricalTree.left = new TreeNode(2);
        symmetricalTree.left.right = new TreeNode(6);

        symmetricalTree.right = new TreeNode(2);

        symmetricalTree.right.left = new TreeNode(6);
        //bstRoot.right.right = new TreeNode(70);

        return symmetricalTree;
    }

    public static TreeNode getHeightBalancedTree(){

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(5);

        return root;
    }


    public static TreeNode getNotHeightBalancedTree(){

        /*
                        2
                      /   \
                     3     5
                    /       \
                   4         6
                  / \         \
                 1   0         7
                                \
                                 -8
                                  \
                                   -3
         */

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(0);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.right.right.right.right = new TreeNode(-8);
        root.right.right.right.right.right = new TreeNode(-3);

        return root;
    }

    public static TreeNode getCompleteBinaryTree(){

        /*
                            1
                          /   \
                         2     3
                        /\     /\
                       4  5   6  7
         */

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public static TreeNode getPathSum(){

        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(2);
        root.right = new TreeNode(10);

        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(-25);

        root.right.right.left = new TreeNode(-3);
        root.right.right.right = new TreeNode(-4);

        return root;
    }


    public static TreeNode getTreeForSumAtMaxDepth(){

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(6);

        root.left.left.left = new TreeNode(5);

        root.right.right.left = new TreeNode(-3);
        root.right.right.right = new TreeNode(8);

        return root;
    }
}
