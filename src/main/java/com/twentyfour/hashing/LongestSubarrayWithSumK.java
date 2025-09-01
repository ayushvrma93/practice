package com.twentyfour.hashing;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithSumK {

    public int get(int[] arr, int k){

        int sum = 0;
        int max = -1;
        int len = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<arr.length; i++){
            sum+=arr[i];
            if(sum==k){
                len = i+1;
            } else {
                int remove = sum - k;
                if (map.containsKey(remove)) {
                    len = i - map.get(remove);
                }
            }
            max = Math.max(max, len);

            if(!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubarrayWithSumK longestSubarrayWithSumK = new LongestSubarrayWithSumK();
        int[] arr1 = {2,3,5,1,9};
        int[] arr2 = {2,3,5};
        int[] arr3 = {1,-1,1};
        System.out.println(longestSubarrayWithSumK.get(arr1, 10));
        System.out.println(longestSubarrayWithSumK.get(arr2, 5));
        System.out.println(longestSubarrayWithSumK.get(arr3, 1));
    }
}
