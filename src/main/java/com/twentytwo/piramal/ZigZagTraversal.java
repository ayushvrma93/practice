package com.twentytwo.piramal;

import com.twentytwo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ZigZagTraversal {

    public List<Integer> getTraversal(TreeNode root){

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        List<Integer> result = new ArrayList<>();

        boolean ltr = true;

        stack1.push(root);

        while (!stack1.isEmpty()){

            TreeNode curr = stack1.pop();
            result.add(curr.data);

            if(ltr){
                if(curr.left != null){
                    stack2.push(curr.left);
                }
                if(curr.right != null){
                    stack2.push(curr.right);
                }
            }

            else{
                if(curr.right != null){
                    stack2.push(curr.right);
                }
                if(curr.left != null){
                    stack2.push(curr.left);
                }
            }

            Stack<TreeNode> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
            ltr = !ltr;
        }
        return result;
    }
}
