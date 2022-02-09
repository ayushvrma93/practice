package com.linkedlist;

public class MergeSort {

    public Node mergeSort(Node head){

        if(head == null || head.next == null)
            return head;

        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;

        mid.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMid);

        Node sortedList = sortedMerge(left, right);
        return sortedList;
    }


    private Node sortedMerge(Node head1, Node head2) {

        if(head1 == null) return head2;
        if(head2 == null) return head1;

        if(head1.data <= head2.data){
            head1.next = sortedMerge(head1.next, head2);
            return head1;
        }
        else {
            head2.next = sortedMerge(head1, head2.next);
            return head2;
        }
    }


    private Node getMiddle(Node head) {

        Node sp = head;
        Node fp = head;

        while(fp.next != null && fp.next.next != null){
            sp = sp.next;
            fp = fp.next.next;
        }
        return sp;
    }


    public static void main(String[] args) {

        Node linkedList = Node.createLL();
        MergeSort mergeSort = new MergeSort();
        Node sortedList = mergeSort.mergeSort(linkedList);

        Node.printList(sortedList);
    }
}
