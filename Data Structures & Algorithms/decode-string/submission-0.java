class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        String curr = "";
        int num = 0;
        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '['){
                countStack.push(num);
                stringStack.push(curr);
                curr = "";
                num = 0;
            }else if(ch == ']'){
                int repeat = countStack.pop();
                String temp = "";
                String prev = stringStack.pop();
                for(int i = 0; i < repeat; i++){
                    temp += curr;
                }
                curr = prev + temp;
            }else{
                curr += ch;
            }
        }
        return curr;
    }
}