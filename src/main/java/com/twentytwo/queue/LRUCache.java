package com.twentytwo.queue;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LRUCache<T> {

    private Deque<T> doublyLinkedList;
    private Set<T> hashSet;
    private final int CACHE_SIZE;

    LRUCache(int size){
        doublyLinkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = size;

    }

    public Deque<T> getDoublyLinkedList(){
        return doublyLinkedList;
    }

    public void implement(T page){

        if(!hashSet.contains(page)){
            if(doublyLinkedList.size() == CACHE_SIZE){
                T last = doublyLinkedList.removeLast();
                hashSet.remove(last);
            }
            hashSet.add(page);
        }
        else {
            doublyLinkedList.remove(page);
        }
        doublyLinkedList.addFirst(page);
    }

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(3);

        for(int i = 0; i < 5; i++){
            lruCache.implement(i);
            System.out.println(lruCache.getDoublyLinkedList());
        }
    }
}
