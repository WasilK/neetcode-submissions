class StockSpanner {

    Stack<Integer> s;
    List<Integer> list;

    public StockSpanner() {
        s = new Stack<>();
        list = new ArrayList<>();
    }
    
    public int next(int price) {
        list.add(price);
        int i = list.size() - 1;

        while(!s.isEmpty() && price >= list.get(s.peek())){
            s.pop();
        }

        int span = (s.isEmpty() ? i + 1 : i - s.peek());
        s.push(i);
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */