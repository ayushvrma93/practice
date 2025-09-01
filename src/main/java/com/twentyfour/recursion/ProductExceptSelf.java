package com.twentyfour.recursion;

import com.twentytwo.Utility;

public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] postfix = new int[n];

        int[] result = new int[n];

        prefix[0] = nums[0];
        for(int i=1; i<n; i++){
            prefix[i] = prefix[i-1] * nums[i];
        }

        postfix[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--){
            postfix[i] = postfix[i+1] * nums[i];
        }

        result[0] = postfix[1];
        result[n-1] = prefix[n-2];

        for(int i=1; i<n-1; i++){
            result[i] = prefix[i-1] * postfix[i+1];
        }

        return result;
    }

    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        int[] nums = {1,2,3,4};
        int[] res = productExceptSelf.productExceptSelf(nums);

        Utility.printIntArray(res);
    }
}
