class MyQueue {
    static Stack<Integer> s;
    public MyQueue() {
        s = new Stack<>();
    }
    
    public void push(int x) {
        pushAtBottom(s, x);
    }

    public void pushAtBottom(Stack<Integer> s, int x){
        if(s.isEmpty()){
            s.push(x);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, x);
        s.push(top);
    }
    
    public int pop() {
        int val = s.pop();
        return val;
    }
    
    public int peek() {
        return s.peek();
    }
    
    public boolean empty() {
        return s.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */