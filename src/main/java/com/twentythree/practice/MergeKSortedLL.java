package com.twentythree.practice;

import com.twentytwo.linkedlist.Node;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLL {

    public Node mergeKLists(Node[] lists) {

        if(lists == null || lists.length == 0) return null;

        Node res = new Node(-1);
        Node head = res;
        Queue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.data));

        for(int i=0; i<lists.length; i++){
            q.add(lists[i]);
        }

        while(!q.isEmpty()){

            Node curr = q.poll();
            head.next = curr;
            head = head.next;

            if(curr.next != null){
                q.add(curr.next);
            }
        }
        return res.next;
    }

    public static void main(String[] args) {
        MergeKSortedLL merge = new MergeKSortedLL();

        Node head1 = new Node(1);
        head1.next = new Node(4);
        head1.next.next = new Node(5);

        Node head2 = new Node(1);
        head2.next = new Node(3);
        head2.next.next = new Node(4);
        //head2.next.next.next = new Node(8);

        Node head3 = new Node(2);
        head3.next = new Node(6);

        Node[] list = new Node[3];
        list[0] = head1;
        list[1] = head2;
        list[2] = head3;
        Node.printList(merge.mergeKLists(list));
    }
}
