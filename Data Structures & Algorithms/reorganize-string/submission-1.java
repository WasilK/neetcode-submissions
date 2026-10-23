class Solution {
    public String reorganizeString(String s) {

        // ---------------------------------------------------
        // STEP 1: Count the frequency of every character
        // ---------------------------------------------------

        // freq[i] = how many times character ('a' + i) occurs
        //
        // Example:
        // s = "aaabbc"
        //
        // freq['a' - 'a'] = 3
        // freq['b' - 'a'] = 2
        // freq['c' - 'a'] = 1

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }


        // ---------------------------------------------------
        // STEP 2: Create a MAX HEAP
        // ---------------------------------------------------

        // We want the character with the HIGHEST frequency
        // at the top of the heap.
        //
        // int[0] -> character index
        // int[1] -> frequency
        //
        // Example:
        // {0, 3} -> character 'a', frequency 3
        // {1, 2} -> character 'b', frequency 2

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[1] - a[1]
        );


        // Put every character having frequency > 0
        // into the max heap.

        for (int i = 0; i < 26; i++) {

            if (freq[i] > 0) {

                // Store:
                // i       -> character
                // freq[i] -> frequency

                pq.offer(new int[]{i, freq[i]});
            }
        }


        // ---------------------------------------------------
        // STEP 3: Build the answer
        // ---------------------------------------------------

        StringBuilder result = new StringBuilder();


        // Why do we take TWO characters?
        //
        // Suppose heap contains:
        //
        // a -> 3
        // b -> 2
        // c -> 1
        //
        // If we take 'a' again immediately:
        //
        // a a   <-- INVALID
        //
        // So we take the two most frequent DIFFERENT
        // characters and place them next to each other:
        //
        // a b
        //
        // This separates the occurrences of 'a'.

        while (pq.size() >= 2) {

            // Take the character with the highest frequency
            int[] first = pq.poll();

            // Take the character with the second highest frequency
            int[] second = pq.poll();


            // Put both characters into the answer.
            //
            // first[0] is the character index.
            //
            // Example:
            // first[0] = 0
            // 0 + 'a' = 'a'

            result.append((char) (first[0] + 'a'));
            result.append((char) (second[0] + 'a'));


            // We used each character ONCE,
            // so decrease their frequencies.

            first[1]--;
            second[1]--;


            // If first character still has occurrences left,
            // put it back into the heap.
            //
            // Example:
            //
            // a had frequency 3
            // We used one 'a'
            // Now frequency = 2
            //
            // So put (a,2) back.

            if (first[1] > 0) {
                pq.offer(first);
            }


            // Same thing for second character.

            if (second[1] > 0) {
                pq.offer(second);
            }
        }


        // ---------------------------------------------------
        // STEP 4: Handle the last remaining character
        // ---------------------------------------------------

        // At this point, the heap can contain:
        //
        // 0 characters -> everything is already placed
        //
        // OR
        //
        // 1 character -> one character is left


        if (!pq.isEmpty()) {

            int[] last = pq.poll();


            // If more than ONE occurrence remains,
            // we cannot place them without creating
            // adjacent duplicates.
            //
            // Example:
            //
            // result = "ab"
            // remaining = "aa"
            //
            // We would have to do:
            //
            // "abaa"
            //     ^^
            //     duplicate
            //
            // Therefore return "".

            if (last[1] > 1) {
                return "";
            }


            // Only ONE occurrence remains,
            // so safely append it.

            result.append((char) (last[0] + 'a'));
        }


        // Return the reorganized string

        return result.toString();
    }
}