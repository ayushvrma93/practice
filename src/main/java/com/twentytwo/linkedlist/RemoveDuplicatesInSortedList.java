package com.twentytwo.linkedlist;

public class RemoveDuplicatesInSortedList {

    public Node removeDuplicates(Node head){

        if(head == null || head.next == null) return head;

        Node first = head;
        Node second = head.next;
        Node prev = head;
        boolean isRemovalRequired = false;

        while(second != null) {

            while (second != null && first.data == second.data) {
                prev = second;
                second = second.next;
                isRemovalRequired = true;
            }

            if (isRemovalRequired) {
                first.next = second;
                prev.next = null;

            }
            first = first.next;
            if (second != null)
                second = second.next;
        }
        return head;
    }


    public Node removeDuplicates2(Node head){

        if(head == null || head.next == null) return head;

        Node first = head;
        Node second = head.next;
        Node f_prev = null;
        boolean isRemovalRequired = false;

        while(second != null) {

            while (second != null && first.data == second.data) {
                second = second.next;
                isRemovalRequired = true;
            }

            if(isRemovalRequired && f_prev == null){
                head = second;
                first = head;
            }
            else if(isRemovalRequired && f_prev != null) {
                f_prev.next = second;
                if(first != null) first = second;
            }

            if(!isRemovalRequired && first != null) {
                f_prev = first;
                first = first.next;
            }
            if (second != null){
                second = second.next;
            }
            isRemovalRequired = false;
        }
        return head;
    }


    public Node remove(Node head){

        Node curr = head;

        while(curr != null){

            Node temp = curr;

            while(temp != null && curr.data == temp.data){
                temp = temp.next;
            }

            curr.next = temp;
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {

        Node linkedList = Node.createLL();
        Node linkedListCopy = Node.copy(linkedList);
        RemoveDuplicatesInSortedList remove = new RemoveDuplicatesInSortedList();
        Node head = remove.removeDuplicates2(linkedList);
        Node head2 = remove.remove(linkedListCopy);
        Node.printList(head);
        Node.printList(head2);
    }
}
