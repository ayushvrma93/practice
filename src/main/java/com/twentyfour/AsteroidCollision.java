package main.java.com.twentyfour;

import com.twentytwo.Utility;

import java.util.Stack;

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        int i=0;
        int n = asteroids.length;

        while(i<n){
            while(i<n && asteroids[i] > 0){
                st.push(asteroids[i]);
                i++;
            }

            while (asteroids[i] < 0 && !st.isEmpty()){
                pushMax(st, st.peek(), asteroids[i]);
                i++;
            }

            if(st.isEmpty()){
                st.push(asteroids[i]);
            }
            i++;
        }

        int[] result = new int[st.size()];
        int j=st.size()-1;
        while(!st.isEmpty()){
            result[j--] = st.pop();
        }

        return result;
    }

    public void pushMax(Stack<Integer> st, int peeked, int arrayElement){
        int aAbs = Math.abs(peeked);
        int bAbs = Math.abs(arrayElement);

        if(aAbs == bAbs) {
            st.pop();
            return;
        }

        if(aAbs > bAbs) return;

        st.pop();
        st.push(arrayElement);
    }

    public static void main(String[] args) {
        AsteroidCollision asteroidCollision = new AsteroidCollision();
        int[] asteroid1 = {5,10,-5};
        int[] asteroid2 = {8,-8};
        int[] asteroid3 = {10,2,-5};

        int[] res1 = asteroidCollision.asteroidCollision(asteroid1);
        Utility.printIntArray(res1);

        int[] res2 = asteroidCollision.asteroidCollision(asteroid2);
        Utility.printIntArray(res2);

        int[] res3 = asteroidCollision.asteroidCollision(asteroid3);
        Utility.printIntArray(res3);
    }
}
