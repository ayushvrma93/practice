package com.linkedlist;

import java.util.Scanner;

public class Node {

    public int data;
    public Node next;

    Node(int data){
        this.data = data;
    }

    public static void printList(Node head){

        if(null == head){
            System.out.println("Head is null");
            return;
        }

        Node node = head;

        while(null != node){
            System.out.print(node.data + "-> ");
            node = node.next;
        }
    }

    public static Node reverse(Node head){

        if(head == null || head.next == null)
            return head;

        Node current = head;
        Node prev = null;
        Node next;

        while(current != null){

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static Node createLL(){

        System.out.println("Enter the number of nodes you want");
        Scanner sc = new Scanner(System.in);

        int noOfNodes = sc.nextInt();

        Node head = null;
        Node curr = null;

        while (noOfNodes-- != 0){
            System.out.println("Enter node value");
            int val = sc.nextInt();

            if(head == null){
                head = new Node(val);
                curr = head;
            }

            else{
                curr.next = new Node(val);
                curr = curr.next;
            }
        }
        return head;
    }

    public static Node copy(Node head){

        Node newHead = new Node(-1);
        Node curr = head;
        Node newListPtr = newHead;

        while (curr != null){
            newListPtr.next = new Node(curr.data);
            curr = curr.next;
            newListPtr = newListPtr.next;
        }
        return newHead.next;
    }

}
