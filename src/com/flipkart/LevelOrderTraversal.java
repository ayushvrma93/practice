package com.flipkart;

import com.tree.TreeNode;

import java.util.*;

public class LevelOrderTraversal {

    public Map<Integer, List<Integer>> traverse(TreeNode root){

        Map<Integer, List<Integer>> levels = new HashMap<>();
        int currLevel = 0;

        Queue<TreeNode> level = new LinkedList<>();

        level.add(root);

        while(!level.isEmpty()){

            int size = level.size();
            List<Integer> currLevelList = new ArrayList<>();

            while(size-- > 0){

                TreeNode currNode = level.poll();

                currLevelList.add(currNode.data);

                if(currNode.left != null){
                    level.add(currNode.left);
                }

                if(currNode.right != null){
                    level.add(currNode.right);
                }

            }
            levels.put(currLevel, currLevelList);
            currLevel++;
        }
        return levels;
    }

    public static void main(String[] args) {

        LevelOrderTraversal traversal = new LevelOrderTraversal();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Map<Integer, List<Integer>> traversalOrder = traversal.traverse(root);

        System.out.println(traversalOrder);
    }
}
