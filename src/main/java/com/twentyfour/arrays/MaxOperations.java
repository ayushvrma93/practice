package com.twentyfour.arrays;

import java.util.HashMap;
import java.util.Map;

public class MaxOperations {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int result = 0;

        for(int i=0; i<n; i++){
            int req = k-nums[i];
            if(map.containsKey(req)){
                int occ = map.get(req);
                if(occ == 1){
                    map.remove(req);
                } else{
                    map.put(req, occ - 1);
                }
                result++;
            } else{
                map.put(nums[i], map.getOrDefault(nums[i], 0) +1);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        MaxOperations maxOperations = new MaxOperations();
        int[] arr1 = {2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2};
        System.out.println(maxOperations.maxOperations(arr1, 3));
    }
}
