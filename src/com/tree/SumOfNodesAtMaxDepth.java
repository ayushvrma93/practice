package com.tree;

public class SumOfNodesAtMaxDepth {

    public int getSumUtil(TreeNode root, int sum, int height){

        if(root == null) return 0;

        if(height == 1){
            sum += root.data;
            return sum;
        }
        return getSumUtil(root.left, sum, height - 1) + getSumUtil(root.right, sum, height - 1);
    }


    public int height(TreeNode root){

        if(root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(lh, rh) + 1;
    }


    public int getSum(TreeNode root){

        int height = height(root);
        int sum = getSumUtil(root, 0, height);
        return sum;
    }

    public int getSumAtMaxDepth(TreeNode root){

        if(root == null) return 0;

        if(root.left == null && root.right == null) return root.data;

        return getSumAtMaxDepth(root.left) + getSumAtMaxDepth(root.right);
    }


    public static void main(String[] args) {

        SumOfNodesAtMaxDepth sumOfNodes = new SumOfNodesAtMaxDepth();

        TreeNode tree1 = Tree.getNotHeightBalancedTree();
        TreeNode tree2 = Tree.getTreeForSumAtMaxDepth();

        //System.out.println(sumOfNodes.getSum(tree1));
        //System.out.println(sumOfNodes.getSum(tree2));

        System.out.println(sumOfNodes.getSumAtMaxDepth(tree1));
        System.out.println(sumOfNodes.getSumAtMaxDepth(tree2));
    }
}
