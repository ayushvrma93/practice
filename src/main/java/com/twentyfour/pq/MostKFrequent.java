package com.twentyfour.pq;

import com.twentytwo.Utility;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MostKFrequent {

    class Obj implements com.twentyfour.pq.Obj {
        int num;
        int freq;

        public Obj(int num, int freq){
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int hashCode(){
            return this.num;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == this) return true;

            if(!(obj instanceof Obj)){
                return false;
            }

            return this.num == ((Obj) obj).num;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> freqMap = new HashMap<>();
        int[] res = new int[k];

        Comparator<Obj> reverseComparator = (o1, o2) -> Integer.compare(o2.freq, o1.freq);
        PriorityQueue<Obj> pq = new PriorityQueue<>(reverseComparator);

        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        for(Map.Entry<Integer, Integer> e : freqMap.entrySet()){
            Obj obj = new Obj(e.getKey(), e.getValue());
            pq.add(obj);
        }

        for(int i=0; i<k; i++){
            res[i] = pq.remove().num;
        }

        return res;
    }

//    public int[] topKFrequentNoHM(int[] nums, int k) {
//        int[] res = new int[k];
//
//        Comparator<Obj> reverseComparator = (o1, o2) -> Integer.compare(o2.freq, o1.freq);
//        PriorityQueue<Obj> pq = new PriorityQueue<>(reverseComparator);
//
//        for (int i = 0; i < nums.length; i++) {
//            Obj obj = new Obj(nums[i], 0);
//            if(pq.contains(obj)){
//                Obj exist = pq.element();
//            }
//        }
//    }

    public static void main(String[] args) {
        MostKFrequent mostKFrequent = new MostKFrequent();
        int[] nums1 = {1,1,1,2,2,3};
        int[] nums2 = {1};
        Utility.printIntArray(mostKFrequent.topKFrequent(nums1, 2));
        Utility.printIntArray(mostKFrequent.topKFrequent(nums2, 1));
    }
}
