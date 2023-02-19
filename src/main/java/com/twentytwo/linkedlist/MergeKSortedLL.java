package com.twentytwo.linkedlist;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeKSortedLL {

    public Node getSorted(Node[] arr){

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> {
            Integer val1 = o1.data;
            Integer val2 = o2.data;

            return Integer.compare(val1, val2);
        });

        Node result = new Node(Integer.MIN_VALUE);
        Node head = result;


        for(Node curr : arr){
            q.add(curr);
        }

        if(q.isEmpty()) return null;

        while(!q.isEmpty()){

            Node curr = q.poll();
            head.next = curr;
            head = head.next;

            if(curr.next != null) q.add(curr.next);
        }
        return result.next;
    }

    public static void main(String[] args) {

        MergeKSortedLL mergeKSortedLL = new MergeKSortedLL();

        System.out.println("Enter the number of elements in the array");
        Scanner sc = new Scanner(System.in);

        Integer size = sc.nextInt();

        Node[] arr = new Node[size];

        for(int i = 0; i< size; i++){
            arr[i] = Node.createLL();
        }

        Node result = mergeKSortedLL.getSorted(arr);

        Node.printList(result);
    }
}
