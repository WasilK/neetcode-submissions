class StockSpanner {
    Stack<Integer> s;
    ArrayList<Integer> prices;
    public StockSpanner() {
        s = new Stack<>();
        prices = new ArrayList<>();
    }
    
    public int next(int price) {
        prices.add(price);
        int i = prices.size() - 1;

        while(!s.isEmpty() && prices.get(s.peek()) <= price){
            s.pop();
        }
        int span = (s.isEmpty()) ? i + 1 : i - s.peek();
        s.push(i);
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */