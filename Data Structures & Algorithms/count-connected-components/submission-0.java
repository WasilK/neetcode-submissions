class Edge {
    int src;
    int dest;
    public Edge(int src, int dest){
        this.src = src;
        this.dest = dest;
    }
}
class Solution {
    public int countComponents(int n, int[][] edges) {
        ArrayList<Edge> graph[] = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(new Edge(a, b));
            graph[b].add(new Edge(b, a));
        }
        int cnt = 0;
        boolean vis[] = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!vis[i]){
                dfs(graph, i, vis);
                cnt++;
            }
        }
        return cnt;
    }
    void dfs(ArrayList<Edge> graph[], int curr, boolean vis[]){
        vis[curr] = true;
        
        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                dfs(graph, e.dest, vis);
            }
        }
    }
}
