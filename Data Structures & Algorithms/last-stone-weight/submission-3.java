class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int st : stones){
            pq.offer(st);
        }
        if(pq.size() == 1){
            return pq.poll();
        }
        while(pq.size() > 1){
            int y = pq.poll();
            int x = pq.poll();
            if(x < y){
                pq.offer(y - x);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
