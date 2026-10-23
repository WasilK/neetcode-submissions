class Solution {
    public int characterReplacement(String s, int k) {

        // Stores the frequency of each character in the current window
        HashMap<Character, Integer> count = new HashMap<>();

        // l = left pointer of sliding window
        // maxf = maximum frequency of a single character in the current window
        // res = answer (maximum valid window length)
        int l = 0, maxf = 0, res = 0;

        // Expand the window using the right pointer
        for (int r = 0; r < s.length(); r++) {

            // Add current character to the window
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);

            // Update the maximum frequency of any character seen in the window
            // Example:
            // Window = "AAB"
            // Frequencies -> A = 2, B = 1
            // maxf = 2
            maxf = Math.max(maxf, count.get(s.charAt(r)));

            /*
             * Window Size = (r - l + 1)
             *
             * maxf = count of the most frequent character.
             *
             * To make the whole window consist of one character,
             * every other character must be replaced.
             *
             * Number of replacements needed =
             * Window Size - Most Frequent Character Count
             *
             * Formula:
             * (r - l + 1) - maxf
             *
             * If replacements needed > k,
             * the window is invalid, so shrink it.
             */
            while ((r - l + 1) - maxf > k) {

                // Remove the leftmost character from the window
                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);

                // Move left pointer to shrink the window
                l++;
            }

            // Window is valid here.
            // Update the maximum valid window length.
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}