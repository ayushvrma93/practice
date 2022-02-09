package com.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ZigZagTraversal {

    public void byDeque(TreeNode root){

        Deque<TreeNode> dq = new LinkedList<>();
        boolean flag = false;

        dq.add(root);

        while(!dq.isEmpty()){

            int size = dq.size();

            for(int i = 0; i< size; i++){

                TreeNode currNode;

                if(flag)
                    currNode = dq.pollFirst();

                else currNode = dq.pollLast();

                System.out.print(currNode.data + " -> ");

                if(flag){
                    if(currNode.right != null)
                        dq.addLast(currNode.right);

                    if(currNode.left != null)
                        dq.addLast(currNode.left);
                }

                else {
                    if(currNode.left != null)
                        dq.addFirst(currNode.left);

                    if(currNode.right != null)
                        dq.addFirst(currNode.right);
                }
            }
            flag = !flag;
        }
        System.out.println();
    }


    public void byStacks(TreeNode root){

        Stack<TreeNode> currLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();

        boolean lToR = true;

        currLevel.add(root);

        while(!currLevel.isEmpty()){

            TreeNode currNode = currLevel.pop();
            System.out.print(currNode.data + " -> ");

            if(lToR){
                if(currNode.left != null)
                    nextLevel.push(currNode.left);

                if(currNode.right != null)
                    nextLevel.push(currNode.right);
            }

            else{
                if(currNode.right != null)
                    nextLevel.push(currNode.right);

                if(currNode.left != null)
                    nextLevel.push(currNode.left);
            }

            if(currLevel.isEmpty()){

                lToR = !lToR;

                Stack<TreeNode> temp = currLevel;
                currLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }

    public static void main(String[] args) {

        TreeNode tree1 = Tree.getHeightBalancedTree();
        TreeNode tree2 = Tree.getCompleteBinaryTree();

        ZigZagTraversal zigZagTraversal = new ZigZagTraversal();

        //zigZagTraversal.byDeque(tree1);
        //zigZagTraversal.byDeque(tree2);
        zigZagTraversal.byStacks(tree2);
    }
}
