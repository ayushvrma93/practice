package com.twentytwo.tree;

public class LCA {

    boolean node1 = false;
    boolean node2 = false;

    private TreeNode findLCAUtil(TreeNode root, int n1, int n2){

        if(root == null) return null;

        TreeNode temp = null;

        if(root.data == n1){
            node1 = true;
            temp = root;
        }

        if(root.data == n2){
            node2 = true;
            temp = root;
        }

        TreeNode left = findLCAUtil(root.left, n1, n2);
        TreeNode right = findLCAUtil(root.right, n1, n2);

        if(temp != null) return temp;

        if(left != null && right != null) return root;

        return left != null ? left : right;
    }


    public TreeNode findLCA(TreeNode root, int n1, int n2){

        node1 = false;
        node2 = false;

        TreeNode lca = findLCAUtil(root, n1, n2);

        if(!node1 || !node2) {
           System.out.println("Tree does not contain both the nodes");
           return null;
        }
        return lca;
    }


    public static void main(String[] args) {

        LCA lca = new LCA();
        TreeNode root = Tree.getHeightBalancedTree();

        System.out.println(lca.findLCA(root, 3, 4).data);
        System.out.println(lca.findLCA(root, 4, 5).data);
        System.out.println(lca.findLCA(root, 4, 6));
    }
}
