class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();

        for(String t : tokens){
            if(t.equals("+")){
                int val1 = s.pop();
                int val2 = s.pop();
                s.push(val1 + val2);
            }else if(t.equals("-")){
                int val1 = s.pop();
                int val2 = s.pop();
                s.push(val2 -  val1);
            }else if(t.equals("*")){
                int val1 = s.pop();
                int val2 = s.pop();
                s.push(val1 * val2);
            }else if(t.equals("/")){
                int val1 = s.pop();
                int val2 = s.pop();
                s.push(val2 / val1);
            }else{
                int val = Integer.parseInt(t);
                s.push(val);
            }
        }
        return s.peek();
    }
}
