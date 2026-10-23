class Solution {
    public String minWindow(String s, String t) {

        // If t is longer than s, it is impossible to find a valid window.
        if (t.length() > s.length()) return "";

        // Stores the frequency of each character required from t.
        HashMap<Character, Integer> tCount = new HashMap<>();

        // Stores the frequency of characters inside the current sliding window.
        HashMap<Character, Integer> window = new HashMap<>();

        // Build the frequency map of t.
        for (char ch : t.toCharArray()) {
            tCount.put(ch, tCount.getOrDefault(ch, 0) + 1);
        }

        // Stores the starting and ending index of the minimum window found.
        int[] res = {-1, -1};

        // Length of the minimum window.
        int len = Integer.MAX_VALUE;

        // Left pointer of the sliding window.
        int l = 0;

        /*
         * need = Number of distinct characters that must satisfy their frequency.
         * Example:
         * t = "AABC"
         * tCount = {A=2, B=1, C=1}
         * need = 3 (NOT 4)
         */
        int need = tCount.size();

        /*
         * have = Number of distinct characters whose required frequency
         * has been satisfied in the current window.
         *
         * When have == need,
         * the current window contains every required character with
         * at least the required frequency.
         */
        int have = 0;

        // Expand the window by moving the right pointer.
        for (int r = 0; r < s.length(); r++) {

            char ch = s.charAt(r);

            // Include the current character in the window.
            window.put(ch, window.getOrDefault(ch, 0) + 1);

            /*
             * Increase "have" ONLY when this character has JUST reached
             * its required frequency.
             *
             * Example:
             * t = "AABC"
             *
             * Required:
             * A = 2
             *
             * Window:
             * A -> 1  (not enough)
             * A -> 2  (now satisfied -> have++)
             * A -> 3  (already satisfied, don't increase have again)
             */
            if (tCount.containsKey(ch)
                    && window.get(ch).equals(tCount.get(ch))) {
                have++;
            }

            /*
             * If every required character has been satisfied,
             * try shrinking the window from the left
             * to make it as small as possible.
             */
            while (have == need) {

                // Update the answer if this window is smaller.
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    res[0] = l;
                    res[1] = r;
                }

                // Remove the leftmost character from the window.
                window.put(s.charAt(l), window.get(s.charAt(l)) - 1);

                /*
                 * If removing this character makes its frequency
                 * smaller than required,
                 * then the window is no longer valid.
                 *
                 * Therefore decrease "have".
                 */
                if (tCount.containsKey(s.charAt(l))
                        && window.get(s.charAt(l)) < tCount.get(s.charAt(l))) {
                    have--;
                }

                // Continue shrinking.
                l++;
            }
        }

        // If no valid window was found, return "".
        return len == Integer.MAX_VALUE
                ? ""
                : s.substring(res[0], res[1] + 1);
    }
}