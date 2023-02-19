package com.twentytwo.linkedlist;

public class Traversal {

    public Node deleteGivenValue(Node head, int val){

        Node node = head;
        Node prev = null;

        if(node != null && node.data == val){
            head = node.next;
            return head;
        }

        while(node != null){

            if(node.data == val){
                prev.next = node.next;
                return head;
            }

            prev = node;
            node = node.next;
        }

        return head;
    }
}
