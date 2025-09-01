package com.twentyfour.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllAnagramsInString {

    public List<Integer> findAnagrams(String s, String p) {
        int[] index = new int[26];
        Arrays.fill(index, 0);

        int[] pind = new int[26];
        Arrays.fill(pind, 0);
        int plen = p.length();

        List<Integer> res = new ArrayList<>();

        for(int i=0; i<plen; i++){
            pind[p.charAt(i) - 'a']++;
        }

//        for(int i=0; i<plen; i++){
//            System.out.print(pind[i] + " ");
//        }

        int i=0,j=0;
        int n = s.length();

        while(j<n && j<p.length()){
            index[s.charAt(j) - 'a']++;
            j++;
        }

        if(matches(index, pind)){
            res.add(0);
        }

        while(j<n){
            index[s.charAt(j) - 'a']++;
            index[s.charAt(i) - 'a']--;
            if(matches(index, pind)){
                res.add(j-plen+1);
            }
            i++;
            j++;
        }

        return res;

    }

    public boolean matches(int[] a, int[] b){
        for(int i=0;i<a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AllAnagramsInString allAnagramsInString = new AllAnagramsInString();
        System.out.println(allAnagramsInString.findAnagrams("cbaebabacd", "abc"));
    }
}
