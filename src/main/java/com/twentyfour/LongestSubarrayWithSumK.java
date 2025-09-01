package com.twentyfour;

public class LongestSubarrayWithSumK {

    public static int lenOfLongSubarr (int A[], int N, int K) {

        int i=0,j=0;
        // int count =0;
        int sum=0;
        int max=0;

        while(j<N){
            sum+=A[j];
            if(sum == K){
                // count++;
                max=j-i+1;
                break;
            }
            j++;
        }

        j++;

        while(j<N){

            sum+=A[j];
            j++;

            while(i<j && sum>K){
                sum-=A[i];
                i++;
            }

            while(j<N && sum<K){
                sum+=A[j];
                j++;
            }

            if(sum == K){
                max=Math.max(max, j-i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 5, 2, 7, 1, 9};
        int[] arr2 = {3, 3, 1, 1, 1, 1, 1, 1};
        System.out.println(lenOfLongSubarr(arr1, 6, 15));
        System.out.println(lenOfLongSubarr(arr2, 8, 6));
    }
}
