class Solution {
    public int orangesRotting(int[][] grid) {
        int minutes = 0;
        int fresh = 0;

        int m = grid.length, n = grid[0].length;

        Queue<int []> q = new LinkedList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i, j});
                }
                if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }

        if(fresh == 0) return 0;

        int directions[][] = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                int curr[] = q.remove();

            int i = curr[0];
            int j = curr[1];

            for(int dir[] : directions){
                int ni = i + dir[0];
                int nj = j + dir[1];

                if(ni < 0 || nj < 0 || ni >= m || nj >= n){
                    continue;
                }

                if(grid[ni][nj] == 1){
                    grid[ni][nj] = 2;
                    fresh--;
                    q.add(new int[]{ni, nj});
                }
               }
            }
            if(!q.isEmpty()){
                minutes++;
            }
        }
        return fresh ==  0 ? minutes : -1;
    }
}
