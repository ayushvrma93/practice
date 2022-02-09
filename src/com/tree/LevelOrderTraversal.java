package com.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public List<List<Integer>> get(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Queue<List<TreeNode>> q = new LinkedList<>();

        List<TreeNode> rootList = new ArrayList<>();

        rootList.add(root);

        q.add(rootList);

        while(!q.isEmpty()){

            List<TreeNode> currList = q.poll();
            List<TreeNode> newList = new ArrayList<>();
            List<Integer> currListVal = new ArrayList<>();

            for(TreeNode currNode : currList){

                currListVal.add(currNode.data);

                if(currNode.left != null){
                    newList.add(currNode.left);
                }

                if(currNode.right != null){
                    newList.add(currNode.right);
                }
            }

            result.add(currListVal);

            if(!newList.isEmpty())
                q.add(newList);
        }

        return result;
    }

    public static void main(String[] args) {

        TreeNode tree = Tree.getNonSymmetricalTree();

        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();

        List<List<Integer>> result = levelOrderTraversal.get(tree);

        for(List<Integer> currList : result){
            for(Integer i : currList){
                System.out.print(i + " ");
            }
        }
    }
}
