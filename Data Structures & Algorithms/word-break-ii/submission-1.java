
class Solution {

    List<String> res;       // Stores all valid sentences
    HashSet<String> set;    // Stores dictionary words for fast lookup

    public List<String> wordBreak(String s, List<String> wordDict) {

        res = new ArrayList<>();
        set = new HashSet<>(wordDict); // Convert dictionary to HashSet

        List<String> curr = new ArrayList<>();
        // Stores the words selected in the current recursive path

        // Start backtracking from index 0
        backtrack(s, 0, set, curr);

        return res; // Return all possible valid sentences
    }

    void backtrack(String s, int idx, HashSet<String> set,
                   List<String> curr) {

        // BASE CASE:
        // If idx reaches the end of the string, we have used
        // the entire string and every selected word is valid.
        if (idx == s.length()) {

            // Join the selected words with spaces and save the sentence
            res.add(String.join(" ", curr));

            return; // Stop this recursive path
        }

        // Try every possible substring starting from index idx
        for (int i = idx; i < s.length(); i++) {

            // Extract a possible word from idx to i (inclusive)
            String w = s.substring(idx, i + 1);

            // Continue only if this substring exists in the dictionary
            if (set.contains(w)) {

                // 1. CHOOSE:
                // Add the valid word to our current sentence
                curr.add(w);

                // 2. EXPLORE:
                // Recursively process the remaining string,
                // starting immediately after the chosen word
                backtrack(s, i + 1, set, curr);

                // 3. UNDO (BACKTRACK):
                // Remove the chosen word to explore other possibilities
                curr.remove(curr.size() - 1);
            }
        }
    }
}
