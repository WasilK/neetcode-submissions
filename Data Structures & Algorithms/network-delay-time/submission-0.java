class Edge {
    int src;
    int dest;
    int wt;

    public Edge(int src, int dest, int wt){
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }
}
class Pair{
    int n;
    int path;
    public Pair(int n, int path){
        this.n = n;
        this.path = path;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<Edge> graph[] = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < times.length; i++){
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            graph[u].add(new Edge(u, v, w));
        }
        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.path, b.path));
        pq.offer(new Pair(k, 0));
        boolean vis[] = new boolean[n + 1];

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            if(!vis[curr.n]){
                vis[curr.n] = true;
                for(int i = 0; i < graph[curr.n].size(); i++){
                    Edge e = graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int w = e.wt;

                    if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]){
                        dist[v] = dist[u] + w;
                        pq.offer(new Pair(v, dist[v]));
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 1; i < dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}
