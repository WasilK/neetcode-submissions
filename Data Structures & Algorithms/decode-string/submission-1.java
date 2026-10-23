class Solution {
    public String decodeString(String s) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();

        int num = 0;
        String curr = "";
        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }else if(ch == '['){
                strStack.push(curr);
                numStack.push(num);
                curr = "";
                num = 0;
            }else if(ch == ']'){
                int repeat = numStack.pop();
                String temp = "";
                String prev = strStack.pop();
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