package com.twentytwo.slidingwindow;

import java.util.Arrays;

public class GFG {

// Java program to find the longest substring with k unique
// characters in a given string

    final static int MAX_CHARS = 26;

    // This function calculates number
    // of unique characters
    // using a associative array
    // count[]. Returns true if
    // no. of characters are less
    // than required else returns
    // false.
    boolean isValid(int count[],
                    int k)
    {
        int val = 0;
        for (int i = 0; i < MAX_CHARS; i++)
        {
            if (count[i] > 0)
            {
                val++;
            }
        }

        // Return true if k is greater
        // than or equal to val
        return (k >= val);
    }

    // Finds the maximum substring
    // with exactly k unique chars
    void kUniques(String s, int k)
    {
        int u = 0;
        int n = s.length();

        // Associative array to store
        // the count of characters
        int count[] = new int[MAX_CHARS];
        Arrays.fill(count, 0);
        // Traverse the string, Fills
        // the associative array
        // count[] and count number
        // of unique characters
        for (int i = 0; i < n; i++)
        {
            if (count[s.charAt(i) - 'a'] == 0)
            {
                u++;
            }
            count[s.charAt(i) - 'a']++;
        }

        // If there are not enough
        // unique characters, show
        // an error message.
        if (u < k) {
            System.out.print("Not enough unique characters");
            return;
        }

        // Otherwise take a window with
        // first element in it.
        // start and end variables.
        int curr_start = 0, curr_end = 0;

        // Also initialize values for
        // result longest window
        int max_window_size = 1;
        int max_window_start = 0;

        // Initialize associative
        // array count[] with zero
        Arrays.fill(count, 0);

        // put the first character
        count[s.charAt(0) - 'a']++;

        // Start from the second character and add
        // characters in window according to above
        // explanation
        for (int i = 1; i < n; i++) {
            // Add the character 's[i]'
            // to current window
            count[s.charAt(i) - 'a']++;
            curr_end++;

            // If there are more than k
            // unique characters in
            // current window, remove from left side
            while (!isValid(count, k)) {
                count[s.charAt(curr_start) - 'a']--;
                curr_start++;
            }

            // Update the max window size if required
            if (curr_end - curr_start + 1 > max_window_size)
            {
                max_window_size = curr_end - curr_start + 1;
                max_window_start = curr_start;
            }
        }

        System.out.println("Max substring is : "
                + s.substring(max_window_start,
                max_window_start + max_window_size)
                + " with length " + max_window_size);
    }

    // Driver Code
    public static void main(String[] args) {

        GFG gfg = new GFG();
        String s = "aabacbebebe";
        String str4 = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        gfg.kUniques(s, 3);
        gfg.kUniques(str4, 5);
    }
}
