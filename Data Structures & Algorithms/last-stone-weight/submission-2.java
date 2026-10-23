class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int st : stones){
            pq.offer(st);
        }
        if(pq.size() == 1){
            return pq.poll();
        }
        while(pq.size() != 1){
            if(pq.size() == 0){
                return 0;
            }
            int y = pq.poll();
            int x = pq.poll();
            if(x < y){
                pq.offer(y - x);
            }
        }
        if(pq.size() == 0) return 0;
        return pq.poll();
    }
}
