class Edge {
    int src;
    int dest;

    public Edge(int src, int dest){
        this.src = src;
        this.dest = dest;
    }
}
class Solution {
    public boolean validTree(int n, int[][] edges) {
        ArrayList<Edge> graph[] = new ArrayList[n];
        int cnt = 0;
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(new Edge(a, b));
            graph[b].add(new Edge(b, a));
        }
        boolean vis[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                if(detectCycle(graph, i, vis, -1)){
                    return false;
                }
                cnt++;
            }
        }
        if(cnt > 1){
            return false;
        }
        return true;
    }

    boolean detectCycle(ArrayList<Edge> graph[], int curr, boolean vis[], int par){
        vis[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                if(detectCycle(graph, e.dest, vis, e.src)){
                    return true;
                }
            }else if(vis[e.dest] && e.dest != par){
                return true;
            }
        }

        return false;
    }
}
