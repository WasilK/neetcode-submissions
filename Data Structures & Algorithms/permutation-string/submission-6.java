class Solution {
    public boolean checkInclusion(String s1, String s2) {

        // If s1 is longer, no permutation of it can fit inside s2.
        if (s1.length() > s2.length()) return false;

        // matches = number of characters (out of 26) whose frequencies
        // are currently equal in both arrays.
        // If matches becomes 26, every character frequency matches,
        // meaning the current window is a permutation of s1.
        int matches = 0;

        int[] countS1 = new int[26];
        int[] countS2 = new int[26];

        // Frequency of characters in s1.
        for (int i = 0; i < s1.length(); i++) {
            countS1[s1.charAt(i) - 'a']++;
        }

        // Frequency of the first window of s2.
        for (int i = 0; i < s1.length(); i++) {
            countS2[s2.charAt(i) - 'a']++;
        }

        // Count how many characters already have equal frequencies.
        for (int i = 0; i < 26; i++) {
            if (countS1[i] == countS2[i]) {
                matches++;
            }
        }

        int l = 0;

        // Slide the window.
        for (int r = s1.length(); r < s2.length(); r++) {

            // If all 26 characters match, we found a permutation.
            if (matches == 26) return true;

            // ==========================
            // Add the new character
            // ==========================
            int index = s2.charAt(r) - 'a';
            countS2[index]++;

            /*
             * There are 3 possible situations:
             *
             * 1) Frequencies become equal
             *    Example:
             *    s1 = 2, s2 = 1 -> increment -> s2 = 2
             *    We gained one match.
             */
            if (countS2[index] == countS1[index]) {
                matches++;
            }

            /*
             * 2) Frequencies were equal but now became different
             *    Example:
             *    s1 = 2, s2 = 2 -> increment -> s2 = 3
             *    We lost one match.
             *
             * Why +1 ?
             * After increment, s2 can only exceed s1 by exactly 1
             * if it was equal before increment.
             */
            else if (countS2[index] == countS1[index] + 1) {
                matches--;
            }

            /*
             * 3) Otherwise do nothing.
             *
             * Example:
             * s1 = 2, s2 = 5 -> increment -> s2 = 6
             *
             * They were already different before,
             * and they are still different now.
             *
             * matches should NOT change.
             *
             * This is why we CANNOT simply write "else".
             * Using else would incorrectly decrease matches even
             * though equality was never lost.
             */


            // ==========================
            // Remove the left character
            // ==========================
            index = s2.charAt(l) - 'a';
            countS2[index]--;

            /*
             * Again there are 3 cases.
             *
             * 1) Frequencies become equal.
             *
             * Example:
             * s1 = 2, s2 = 3 -> decrement -> s2 = 2
             *
             * We gained one match.
             */
            if (countS2[index] == countS1[index]) {
                matches++;
            }

            /*
             * 2) Frequencies were equal but became different.
             *
             * Example:
             * s1 = 2, s2 = 2 -> decrement -> s2 = 1
             *
             * We lost one match.
             *
             * Why -1 ?
             * After decrement, s2 can only become exactly
             * one less than s1 if they were equal before.
             */
            else if (countS2[index] == countS1[index] - 1) {
                matches--;
            }

            /*
             * 3) Otherwise do nothing.
             *
             * Example:
             * s1 = 2, s2 = 6 -> decrement -> s2 = 5
             *
             * Still unequal, so matches remains unchanged.
             */

            l++;
        }

        // Check the final window.
        return matches == 26;
    }
}