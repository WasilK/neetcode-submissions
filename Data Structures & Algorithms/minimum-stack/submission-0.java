class MinStack {
    Queue<Integer> q;
    public MinStack() {
        q = new LinkedList<>();
    }
    
    public void push(int val) {
        q.add(val);
        for(int i = 0; i < q.size() - 1; i++){
            q.add(q.remove());
        }
    }
    
    public void pop() {
        q.remove();
    }
    
    public int top() {
        return q.peek();
    }
    
    public int getMin() {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < q.size(); i++){
            int val = q.remove();
            min = Math.min(min, val);
            q.add(val);
        }
        return min;
    }
}
