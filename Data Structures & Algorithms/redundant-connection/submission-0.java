class Solution {
    static int rank[];
    static int par[];

    int find(int x){
        if(par[x] == x){
            return x;
        }
        return par[x] = find(par[x]);
    }
    boolean union(int u, int v){
        int parU = find(u);
        int parV = find(v);

        if(parU == parV){
            return false;
        }

        if(rank[parU] == rank[parV]){
            par[parV] = parU;
            rank[parU]++;
        }else if(rank[parU] > rank[parV]){
            par[parV] = parU;
        }else{
            par[parU] = parV;
        }
        return true;
    }
    public int[] findRedundantConnection(int[][] edges) {
        int res[] = new int[2];
        par = new int[edges.length + 1];
        rank = new int[edges.length + 1];
        for(int i = 1; i < par.length; i++){
            par[i] = i;
        }
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            if(!union(u, v)){
                res[0] = u;
                res[1] = v;
            }
        }
        return res;
    }
}
