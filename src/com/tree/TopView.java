package com.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TopView {

    public ArrayList<Integer> topView(TreeNode root)
    {
        ArrayList<Integer> result = new ArrayList<>();
        topViewUtil(root, 0, 0, new HashSet<Integer>(), result);
        return result;

    }

    private void topViewUtil(TreeNode root, int verticalLevel, int horizontalLevel, Set<Integer> set, ArrayList<Integer> list){

        if(root == null) return;

        if(!set.contains(verticalLevel)){
            list.add(root.data);
            set.add(verticalLevel);
        }

        topViewUtil(root.left, verticalLevel-1,horizontalLevel+1 , set, list);
        topViewUtil(root.right, verticalLevel+1, horizontalLevel+1, set, list);
    }

    public static void main(String[] args) {

        TopView topView = new TopView();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        System.out.println(topView.topView(root));
    }
}
