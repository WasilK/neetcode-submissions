
class Solution {
    public List<Integer> partitionLabels(String s) {

        List<Integer> res = new ArrayList<>();

        // Step 1: Store the LAST occurrence index of every character.
        // This helps us determine how far a partition must extend
        // to include all occurrences of a character.
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, i);
        }

        // first = starting index of the current partition
        // last  = farthest index the current partition must reach
        int first = 0;
        int last = 0;

        // Step 2: Traverse the string and determine partition boundaries.
        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            // Extend the current partition boundary to include
            // the last occurrence of the current character.
            //
            // We use Math.max because another character encountered
            // earlier might have a farther last occurrence.
            last = Math.max(last, map.get(ch));

            // Step 3: If the current index reaches the boundary,
            // all characters in this partition have their
            // occurrences completely contained within it.
            if(i == last){

                // Store the length of the completed partition.
                res.add(last - first + 1);

                // Start the next partition immediately after this one.
                first = i + 1;
            }
        }

        return res;
    }
}
