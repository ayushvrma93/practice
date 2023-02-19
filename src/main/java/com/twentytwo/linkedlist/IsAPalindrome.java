package com.twentytwo.linkedlist;

public class IsAPalindrome {

    public boolean isPalindrome(Node head){

        Node fast = head;
        Node slow = head;
        Node prev = null;

        if(head.next == null) return true;

        while(fast != null && fast.next != null){

            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        Node head1 = head;
        Node head2 = fast == null ? slow : slow.next;
        prev.next = null;

//        if(fast == null){
//            head2= slow;
//        }

        head2 = Node.reverse(head2);

        Node ptr1 = head1;
        Node ptr2 = head2;

        while(ptr1 != null){

            if(ptr1.data != ptr2.data)
                return false;

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        //head2 = LLNode.reverse(head2);

        return true;

    }

    public static void main(String[] args) {

        IsAPalindrome isAPalindrome = new IsAPalindrome();
        Node head = Node.createLL();

        System.out.println(isAPalindrome.isPalindrome(head));

    }

}
