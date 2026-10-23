class MinStack {
    Stack<Integer> s;
    Stack<Integer> minS;
    public MinStack() {
        s = new Stack<>();
        minS = new Stack<>();
    }
    
    public void push(int val) {
        s.push(val);
        if(minS.isEmpty() || val <= minS.peek()){
            minS.push(val);
        }else{
            minS.push(minS.peek());
        }
    }
    
    public void pop() {
        s.pop();
        minS.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minS.peek();
    }
}
