class Solution {
    public void solve(char[][] board) {

        bfs(board);

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == '1'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    void bfs(char board[][]){
        
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int []> q = new LinkedList<>();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if((i == 0 || i == board.length - 1
                || j == 0 || j == board[0].length - 1) &&
                board[i][j] == 'O'){
                    q.add(new int[] {i, j});
                }
            }
        }

        while(!q.isEmpty()){
            int curr[] = q.poll();

            int r = curr[0];
            int c = curr[1];

            board[r][c] = '1';

            for(int dir[] : directions){
                int nr = dir[0] + r;
                int nc = dir[1] + c;

                if(nr >= 0 && nc >= 0 && nr < board.length &&
                nc < board[0].length && board[nr][nc] == 'O'){
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}
