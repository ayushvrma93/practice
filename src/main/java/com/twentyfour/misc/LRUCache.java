package com.twentyfour.misc;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int val){
            this.key=key;
            this.value=val;
        }
    }

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int currSize;
    final int SIZE;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.SIZE = capacity;
        this.currSize = 0;
    }

    public void delete(Node node){
        Node next = node.next;
        Node prev = node.prev;

        prev.next = next;
        next.prev = prev;
    }

    public void addToHead(Node node){
        Node next = head.next;
        node.next = next;
        node.prev = head;
        head.next = node;
        next.prev = node;

    }

    public Node deleteFromTail(){
        Node node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        node.prev = null;
        node.next = null;

        return node;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            int val = temp.value;
            delete(temp);
            addToHead(temp);
            return val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            delete(temp);
            addToHead(temp);
        }
        else{
            if(SIZE == map.size()){
                Node deletedNode = deleteFromTail();
                map.remove(deletedNode.key);
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                // System.out.println(last);
            } else{
                currSize++;
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
            }
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));  // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
