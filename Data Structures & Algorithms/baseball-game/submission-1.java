class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> s = new Stack<>();
        for(String op : operations){
            if(op.equals("+")){
                int top = s.pop();
                int newTop = top + s.peek();
                s.push(top);
                s.push(newTop);
            }else if(op.equals("C")){
                s.pop();
            }else if(op.equals("D")){
                s.push(s.peek() * 2);
            }else{
                s.push(Integer.parseInt(op));
            }
        }
        int score = 0;
        while(!s.isEmpty()){
            score += s.pop();
        }
        return score;
    }
}