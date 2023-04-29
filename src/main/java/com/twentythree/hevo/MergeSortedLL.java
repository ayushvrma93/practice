package com.twentythree.hevo;

import com.twentytwo.linkedlist.Node;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeSortedLL {

    public Node getMergedLL(Node[] llArr){

        if(llArr == null || llArr.length == 0){
            return null;
        }

        Node res = new Node(Integer.MIN_VALUE);
        Node ptr = res;

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.data));

        for(int i=0; i< llArr.length; i++){
            pq.add(llArr[i]);
        }

        while(!pq.isEmpty()){

            Node curr = pq.poll();
            res.next = curr;
            res = res.next;

            if(curr.next != null){
                pq.add(curr.next);
            }
        }
        return ptr.next;
    }

    public static void main(String[] args) {
        MergeSortedLL merge = new MergeSortedLL();
        Node head1 = Node.createLL();
        Node head2 = Node.createLL();
        Node head3 = Node.createLL();

        Node[] nodes = new Node[3];
        nodes[0] = head1;
        nodes[1] = head2;
        nodes[2] = head3;

        Node res = merge.getMergedLL(nodes);
        Node.printList(res);
    }
}
