class Solution {
    private int directions[][] = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> res = new ArrayList<>();

        int rows = heights.length, cols = heights[0].length;

        boolean pac[][] = new boolean[rows][cols];
        boolean atl[][] = new boolean[rows][cols];

        for(int c = 0; c < cols; c++){
            dfs(0, c, heights, pac);
            dfs(rows - 1, c, heights, atl);
        }
        for(int r = 0; r < rows; r++){
            dfs(r, 0, heights, pac);
            dfs(r, cols - 1, heights, atl);
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(pac[i][j] && atl[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    void dfs(int r, int c, int heights[][], boolean vis[][]){

        vis[r][c] = true;

        for(int dir[] : directions){

            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >= 0 && nc >= 0 && nr < heights.length &&
            nc < heights[0].length && !vis[nr][nc] && heights[nr][nc] >=
            heights[r][c]){
                dfs(nr, nc, heights, vis);
            }
        }
    }
}
