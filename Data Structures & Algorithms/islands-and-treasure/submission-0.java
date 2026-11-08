class Solution {
    public void islandsAndTreasure(int[][] grid) {

        int m = grid.length, n = grid[0].length;

        Queue<int []> q = new LinkedList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i, j});
                }
            }
        }

        int directions[][] = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        while(!q.isEmpty()){

            int curr[] = q.remove();

            int i = curr[0];
            int j = curr[1];

            for(int dir[] : directions){
                int ni = dir[0] + i;
                int nj = dir[1] + j;

                if(ni < 0 || nj < 0 || ni >= m || nj >= n){
                    continue;
                }

                if(grid[ni][nj] == Integer.MAX_VALUE){
                    grid[ni][nj] = grid[i][j] + 1;
                    q.add(new int[]{ni, nj});
                }
            }
        }
    }
}
