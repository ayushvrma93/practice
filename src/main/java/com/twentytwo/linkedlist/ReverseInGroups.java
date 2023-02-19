package com.twentytwo.linkedlist;

import java.util.Stack;

public class ReverseInGroups {

    public Node reverse(Node head, int k){

        if(head == null || k == 0 || k == 1) return head;

        Node curr = head;
        Node next = null;
        Node prev = null;
        int count = k;

        while(curr != null && count-- > 0){

            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if(next != null){
            head.next = reverse(next, k);
        }
        return prev;
    }


    private Node reverseInIncreasingFashionUtil(Node head, int k){
        if(head == null) return null;

        //if(k==1) return head;

        Node curr = head;
        Node prev = null;
        Node next = null;
        int count = k;

        while(curr != null && count-- > 0){

            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if(curr != null){
            head.next = reverseInIncreasingFashionUtil(curr, k);
        }
        return prev;
    }


    public Node reverseGfg(Node head, int k)
    {
        // Create a stack of Node*
        Stack<Node> stack = new Stack<Node> ();
        Node current = head;
        Node prev = null;

        while (current != null)
        {

            // Terminate the loop whichever comes first
            // either current == NULL or count >= k
            int count = 0;
            while (current != null && count < k)
            {
                stack.push(current);
                current = current.next;
                count++;
            }

            // Now pop the elements of stack one by one
            while (stack.size() > 0)
            {

                // If final list has not been started yet.
                if (prev == null)
                {
                    prev = stack.peek();
                    head = prev;
                }
                else
                {
                    prev.next = stack.peek();
                    prev = prev.next;
                }
                stack.pop();
            }
        }

        // Next of last element will point to NULL.
        prev.next = null;

        return head;
    }

    public static void main(String[] args) {

        Node linkedList = Node.createLL();
        Node.printList(linkedList);
        System.out.println();
        ReverseInGroups reverse = new ReverseInGroups();
        //Node result1 = reverse.reverse(linkedList, 2);
        //Node result2 = reverse.reverseInIncreasingFashionUtil(linkedList, 2);
        Node result3 = reverse.reverseInIncreasingFashionUtil(linkedList, 2);
        //Node.printList(result1);
        System.out.println();
        //Node.printList(result2);
        System.out.println("By gfg method: ");
        Node.printList(result3);
    }
}
