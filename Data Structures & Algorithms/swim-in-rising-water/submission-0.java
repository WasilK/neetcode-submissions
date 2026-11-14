class Pair {
    int row;
    int col;
    int time;

    public Pair(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int dist[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = grid[0][0];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
        pq.offer(new Pair(0, 0, grid[0][0]));
        
        int directions[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!pq.isEmpty()){
            Pair curr = pq.poll();

            int r = curr.row;
            int c = curr.col;
            int time = curr.time;

            if(time > dist[r][c]){
                continue;
            }

            if(r == n - 1 && c == n - 1){
                return time;
            }

            for(int i = 0; i < 4; i++){
                int nr = r + directions[i][0];
                int nc = c + directions[i][1];

                if(nr >= 0 && nc >= 0 && nr < n && nc < n){
                    int newTime = Math.max(time, grid[nr][nc]);
                    if(newTime < dist[nr][nc]){
                        dist[nr][nc] = newTime;
                        pq.offer(new Pair(nr, nc, newTime));
                    }
                }
            }
        }
        return -1;
    }
}
