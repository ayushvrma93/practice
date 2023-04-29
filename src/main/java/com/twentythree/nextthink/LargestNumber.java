package com.twentythree.nextthink;

public class LargestNumber {

    public int solution(int N) {

        boolean isNeg = N < 0 ? true : false;
        String number = String.valueOf(N);
        int len = number.length();

        int res = getIndex(number, isNeg, len);

        if(res == -1){
            for(int i=len-1; i>=0; i--){
                if(number.charAt(i) == '5'){
                    res = i;
                    break;
                }
            }
        }

        String answer = "";
        for(int i=0; i<len; i++){
            if(i == res){
                continue;
            }
            answer = answer + number.charAt(i);
        }
        return Integer.valueOf(answer);
    }

    private int getIndex(String number, boolean isNeg, int len) {

        int res = -1;

        if (!isNeg) {
            for (int i = 0; i < len - 1; i++) {
                if (number.charAt(i) == '5' && Character.getNumericValue(number.charAt(i)) < Character.getNumericValue(number.charAt(i + 1))) {
                    res = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < len - 1; i++) {
                if (number.charAt(i) == '5' && Character.getNumericValue(number.charAt(i)) > Character.getNumericValue(number.charAt(i + 1))) {
                    res = i;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        System.out.println(largestNumber.solution(15958));
        System.out.println(largestNumber.solution(-5859));
    }
}
