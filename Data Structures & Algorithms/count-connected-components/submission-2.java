class Solution {
    static int rank[];
    static int par[];
    int find(int x){
        if(par[x] == x){
            return x;
        }
        return par[x] = find(par[x]);
    }
    void union(int a, int b){
        int parA = find(a);
        int parB = find(b);

        if(rank[parA] == rank[parB]){
            par[parB] = parA;
            rank[parA]++;
        }else if(rank[parA] > rank[parB]){
            par[parB] = parA;
        }else{
            par[parA] = parB;
        }
    }
    public int countComponents(int n, int[][] edges) {
        rank = new int[n];
        par = new int[n];

        for(int i = 0; i < n; i++){
            par[i] = i;
        }

        int components = n;
        for(int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            if(find(a) != find(b)){
                union(a, b);
                components--;
            }
        }  
        return components;      
    }
}
