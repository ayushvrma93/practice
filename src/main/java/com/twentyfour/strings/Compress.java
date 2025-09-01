package com.twentyfour.strings;

public class Compress {
    public int compress(char[] chars) {
        int n=chars.length;

        if(n == 1) return 1;

        int count=1, i=0;
        int ind = 0;
//        char curr = chars[0];

        while(i<n){
            while(i<n-1 && chars[i] == chars[i+1]){
                count++;
                i++;
            }
            chars[ind++] = chars[i];
            if(count>1){
                chars[ind++] = (char)(count + '0');
            }
            count = 1;
            i++;
        }

//        if(chars[n-1] == chars[n-2]){
//            chars[--ind] = chars[ind]++;
//        } else {
//            chars[ind++] =  chars[n-1];
//            if(count>1){
//                chars[ind++] = (char)(count + '0');
//            }
//        }
        return ind;

    }

    public static void main(String[] args) {
        Compress compress = new Compress();
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int size = compress.compress(chars1);
        System.out.println(size);
        for(int i=0; i<size; i++){
            System.out.print(chars1[i]);
        }

    }
}
