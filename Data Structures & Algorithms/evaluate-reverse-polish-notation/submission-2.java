class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            String tok = tokens[i];
            if(tok.equals("+")){
                int a = s.pop();
                int b = s.pop();
                s.push(a + b);
            }else if(tok.equals("*")){
                int a = s.pop();
                int b = s.pop();
                s.push(a * b);
            }else if(tok.equals("-")){
                int a = s.pop();
                int b = s.pop();
                s.push(b - a);
            }else if(tok.equals("/")){
                int a = s.pop();
                int b = s.pop();
                s.push(b / a);
            }else{
                s.push(Integer.parseInt(tok));
            }
        }
        return s.peek();
    }
}
