class Solution {

    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return res;
        }

        String[] letters = {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
        };

        StringBuilder sb = new StringBuilder();

        backtrack(digits, 0, letters, sb);

        return res;
    }

    void backtrack(String digits, int idx,
                   String[] letters, StringBuilder sb) {

        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }

        int digit = digits.charAt(idx) - '0';
        String current = letters[digit];

        for (int j = 0; j < current.length(); j++) {

            sb.append(current.charAt(j));

            backtrack(digits, idx + 1, letters, sb);

            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
