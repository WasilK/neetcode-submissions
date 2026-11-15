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
class Pair {
    int n;
    int cost;
    int stops;

    public Pair(int n, int cost, int stops){
        this.n = n;
        this.cost = cost;
        this.stops = stops;
    }
}
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<Edge> graph[] = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < flights.length; i++){
            int s = flights[i][0];
            int d = flights[i][1];
            int w = flights[i][2];
            graph[s].add(new Edge(s, d, w));
        }
        Queue<Pair> q = new LinkedList<>();
        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        q.add(new Pair(src, 0, 0));

        while(!q.isEmpty()){
            Pair curr = q.remove();
            if(curr.stops > k){
                break;
            }
            for(int i = 0; i < graph[curr.n].size(); i++){
                Edge e = graph[curr.n].get(i);
                int v = e.dest;
                int wt = e.wt;
                if(curr.cost + wt < dist[v] && curr.stops <= k){
                    dist[v] = curr.cost + wt;
                    q.add(new Pair(v, dist[v], curr.stops + 1));
                }
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
