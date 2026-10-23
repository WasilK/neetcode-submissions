class Solution {
    public String decodeString(String s) {

        // Stores the string that was built BEFORE encountering '['
        Stack<String> strStack = new Stack<>();

        // Stores the number associated with '['
        Stack<Integer> numStack = new Stack<>();

        // Stores the current number being formed
        // Example: "123[" -> num becomes 123
        int num = 0;

        // Stores the current string we are building
        String curr = "";

        for (char ch : s.toCharArray()) {

            // If we encounter a digit, build the complete number
            // Example: "12[a]" -> num becomes 12
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');

            // We found '['
            } else if (ch == '[') {

                // Save the string that was built before '['
                // Example: "abc3[" -> push "abc"
                strStack.push(curr);

                // Save the repeat count
                // Example: "abc3[" -> push 3
                numStack.push(num);

                // Start building the string inside the brackets
                curr = "";

                // Reset number for the next possible pattern
                num = 0;

            // We found ']'
            } else if (ch == ']') {

                // Get how many times the current string should repeat
                int repeat = numStack.pop();

                // Get the string that existed before '['
                String prev = strStack.pop();

                // Build the repeated string
                String temp = "";

                for (int i = 0; i < repeat; i++) {
                    temp += curr;
                }

                // Combine:
                // previous string + repeated current string
                //
                // Example:
                // prev = "abc"
                // curr = "xy"
                // repeat = 3
                //
                // curr becomes:
                // "abc" + "xyxyxy"
                curr = prev + temp;

            // Normal character
            } else {
                // Add the character to the current string
                curr += ch;
            }
        }

        // After processing the entire input,
        // curr contains the decoded string
        return curr;
    }
}