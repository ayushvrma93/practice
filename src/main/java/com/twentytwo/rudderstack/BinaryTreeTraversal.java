package com.twentytwo.rudderstack;

import com.twentytwo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTraversal {


    /*

                            1
                         2     3
                       3         2

     */

    List<Character> finalPath = new ArrayList<>();

    static class Result{

        int max;
        int mDepth;

        public Result(int max, int mDepth) {
            this.max = max;
            this.mDepth = mDepth;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "max=" + max +
                    ", mDepth=" + mDepth +
                    '}';
        }
    }

    public Result getValues(TreeNode root){

        Result result = new Result(-1, -1);
        List<Character> path = new ArrayList<>();


        traverse(root, result, path, 0);
        System.out.println(finalPath);
        return result;


    }

    private void traverse(TreeNode root, Result result, List<Character> path, int depth){

        if(root == null) return;

        if(root.data > result.max){
            result.max = root.data;
            result.mDepth = depth;
            finalPath = new ArrayList<>(path);
        }

        else if(root.data == result.max && depth > result.mDepth){
            result.mDepth = depth;
            finalPath = new ArrayList<>(path);
        }

        if(root.left != null){
            path.add('l');
            traverse(root.left, result, path, depth+1);
        }

        if(root.right != null) {
            path.add('r');
            traverse(root.right, result, path, depth + 1);
        }
    }

    public static void main(String[] args) {

        BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();

        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(8);
        root.right.left.right = new TreeNode(4);

        System.out.println(binaryTreeTraversal.getValues(root));
    }
}
