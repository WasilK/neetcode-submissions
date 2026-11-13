class Edge{
    int src;
    int dest;
    public Edge(int src, int dest){
        this.src = src;
        this.dest = dest;
    }
}
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int res[] = new int[numCourses];

        int indeg[] = new int[numCourses];

        ArrayList<Edge> graph[] = new ArrayList[numCourses];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < prerequisites.length; i++){
            int val = prerequisites[i][1];
            graph[val].add(new Edge(prerequisites[i][1], prerequisites[i][0]));
        }

        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[i].size(); j++){
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < graph.length; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }

        int k = 0;
        while(!q.isEmpty() && k < res.length){
            int curr = q.remove();
            res[k] = curr;
            k++;
            for(int i = 0; i < graph[curr].size(); i++){
                Edge e = graph[curr].get(i);
                indeg[e.dest]--;
                if(indeg[e.dest] == 0){
                    q.add(e.dest);
                }
            }
        }
        if(k == numCourses){
            return res;
        }
        return new int[0];
    }
}
