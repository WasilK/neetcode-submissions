class Pair{
    int n;
    int cost;
    public Pair(int n, int cost){
        this.n = n;
        this.cost = cost;
    }
}
class Solution {
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.offer(new Pair(0, 0));
        int n = points.length;
        boolean vis[] = new boolean[n];
        int count = 0;
        int minCost = 0;
        while(!pq.isEmpty() && count < n){
            Pair curr = pq.poll();
            if(!vis[curr.n]){
                vis[curr.n] = true;
                minCost += curr.cost;
                count++;
                for(int i = 0; i < n; i++){
                    if(!vis[i]){
                        int dist = Math.abs(points[curr.n][0] - points[i][0]) +
                                   Math.abs(points[curr.n][1] - points[i][1]);
                        pq.offer(new Pair(i, dist));           
                    }
                }
            }
        }
        return minCost;
    }
}
