package com.linkedlist;

public class Manager {

    public static void main(String[] args) {

        Node head = new Node(1);
//        head.next = new LLNode(2);
//        head.next.next = new LLNode(3);
//        head.next.next.next = new LLNode(3);
//        head.next.next.next.next = new LLNode(1);
//        head.next.next.next.next.next = new LLNode(1);

//        Traversal traversal = new Traversal();
//        LLNode result = traversal.deleteGivenValue(head, 65);
//        LLNode.printList(result);

        IsAPalindrome isAPalindrome = new IsAPalindrome();
        boolean isPalindrome = isAPalindrome.isPalindrome(head);
        System.out.println(isPalindrome);
    }
}
