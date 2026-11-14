class Solution {

    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    List<String> res = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> t : tickets){
            String src = t.get(0);
            String dest = t.get(1);
            graph.putIfAbsent(src, new PriorityQueue<>());
            graph.get(src).offer(dest);
        }
        String src = "JFK";
        dfs(src);
        return res;
    }

    void dfs(String src){
        PriorityQueue<String> pq = graph.get(src);
        while(pq != null && !pq.isEmpty()){
            String dest = pq.poll();
            dfs(dest);
        }
        res.addFirst(src);
    }
}
