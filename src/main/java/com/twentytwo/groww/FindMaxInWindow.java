package com.twentytwo.groww;

import java.util.*;

public class FindMaxInWindow {

    public List<Integer> findMax(int[] arr, int k){

        int i =0;
        int j = 1;

        List<Integer> result = new ArrayList<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        pq.add(arr[0]);

        while(j < k){
            pq.add(arr[j]);
            j++;
        }

        int len = arr.length;

        result.add(pq.peek());

        j++;
        pq.remove(arr[i++]);
        pq.add(arr[j]);

        result.add(pq.peek());
        //j++;


        while(i < len - k){

            pq.add(arr[j]);
            pq.remove(arr[i]);
            result.add(pq.peek());
            i++;
            j++;
        }
        return result;
    }

    public List<Integer> byN(int[] arr, int k){

        Deque<Integer> dq = new LinkedList<>();
        int i = 0;
        List<Integer> result = new ArrayList<>();

        while(!dq.isEmpty() && i<k && arr[dq.peekLast()] <= arr[i]){
            dq.removeLast();
        }
        dq.addLast(i);
        i++;

        while(i<arr.length) {

            result.add(dq.peekFirst());

            while (!dq.isEmpty() && i < arr.length && dq.peek() <= i-k){
                dq.removeFirst();
            }

            while(!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]){
                dq.removeLast();
            }
            dq.addLast(i);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {

        FindMaxInWindow findMaxInWindow = new FindMaxInWindow();
        int[] arr = {5,1,8,3,7,5,9};
        int[] arr1 = {8,1,6,3,2,9,11};
        int k =3;
        System.out.println(findMaxInWindow.findMax(arr, k));
        System.out.println(findMaxInWindow.findMax(arr1, k));
    }
}
