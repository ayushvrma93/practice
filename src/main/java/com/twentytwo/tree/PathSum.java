package com.twentytwo.tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

    static List<List<Integer>> finalResult = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum){
        pathSum(root, targetSum, new ArrayList<>());
        return finalResult;
    }

    private boolean pathSum(TreeNode root, int targetSum, List<Integer> result){

        if(root == null) return false;

        if(targetSum - root.data == 0 && root.left == null && root.right == null){
            //if not then the result will be overridden
            List<Integer> temp = new ArrayList<>(result);
            finalResult.add(temp);
            return true;
        }

         if(pathSum(root.left, targetSum - root.data, result)){
             result.add(root.data);
             return true;
         }

         if(pathSum(root.right, targetSum - root.data, result)) {
             result.add(root.data);
             return true;
         }
         return false;
    }


    public void getPathSum(TreeNode root, int targetSum, List<Integer> al){

        if(root == null) return;

        al.add(root.data);

        if(targetSum == root.data && root.left == null && root.right == null){
            List<Integer> temp = new ArrayList<>(al);
            finalResult.add(temp);
        }

        getPathSum(root.left, targetSum - root.data, al);

        getPathSum(root.right, targetSum - root.data, al);

        al.remove(al.size() - 1);
    }


    public static void main(String[] args) {

        TreeNode tree = Tree.getNotHeightBalancedTree();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);


        int targetSum1 = 9;

        PathSum pathSum = new PathSum();

        //List<List<Integer>> result = pathSum.pathSum(tree, targetSum1);
//        pathSum.getPathSum(tree, targetSum1, new ArrayList<>());
        pathSum.getPathSum(root, 22, new ArrayList<>());

        System.out.print(finalResult);

//        for(List<Integer> currList : result){
//            for(Integer i : currList)
//                System.out.print(i + ", ");
//            System.out.println();
//        }
    }
}
