class Solution {
    public int calPoints(String[] operations) {
        int sum = 0;
        Stack<Integer> s = new Stack<>();
        for(String op : operations){
            if(op.equals("+")){
                int n1 = s.pop();
                int n2 = s.peek();
                s.push(n1);
                s.push(n1 + n2);
                sum += s.peek();
            }else if(op.equals("C")){
                sum -= s.peek();
                s.pop();
            }else if(op.equals("D")){
                s.push(s.peek() * 2);
                sum += s.peek();
            }else{
                s.push(Integer.parseInt(op));
                sum += s.peek();
            }
        }
        return sum;
    }
}