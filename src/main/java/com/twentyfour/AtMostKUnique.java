package main.java.com.twentyfour;

import java.util.HashSet;
import java.util.Set;

public class AtMostKUnique {

    public int longestKSubstr(String s, int k) {

        char[] chArr = s.toCharArray();
        int n = chArr.length;

        int l=0,r=0;
        Set<Character> set = new HashSet<>();
        int curr=0;
        int max=0;
        int c=k;

        while(r<n && c > 0){
            if(!set.contains(chArr[r])){
                set.add(chArr[r]);
            } else{
                c--;
            }
        }

        max = r-l+1;
        c=k;

        while(r<n && l<=r){

            if(!set.contains(chArr[r])){
                set.add(chArr[r]);
            } else{
                c--;
            }

            max = Math.max(max, r-l+1);

            while(l<=r && c == 0){
                set.remove(chArr[r]);
                l++;
                c++;
            }

            max = Math.max(max, r-l+1);

            r++;
        }

        return max;

    }

    public static void main(String[] args) {
        AtMostKUnique atMostKUnique = new AtMostKUnique();

    }
}
