class Edge {
    int src;
    int dest;
    public Edge(int src, int dest){
        this.src = src;
        this.dest = dest;
    }
}
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        ArrayList<Edge> graph[] = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n; i++){
            int val = prerequisites[i][1];
            graph[val].add(new Edge(prerequisites[i][1], prerequisites[i][0]));
        }

        boolean vis[] = new boolean[graph.length];
        boolean st[] = new boolean[graph.length];

        for(int i = 0; i < graph.length; i++){
            if(!vis[i]){
                if(detectCycle(graph, i, vis, st)){
                    return false;
                }
            }
        }
        return true;
    }
    boolean detectCycle(ArrayList<Edge> graph[], int curr, boolean vis[], boolean st[]){
        vis[curr] = true;
        st[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(st[e.dest]){
                return true;
            }
            if(!vis[e.dest] && detectCycle(graph, e.dest, vis, st)){
                return true;
            }
        }
        st[curr] = false;
        return false;
    }
}
