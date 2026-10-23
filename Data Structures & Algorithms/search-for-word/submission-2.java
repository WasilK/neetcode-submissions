class Solution {

    /*
     APPROACH: BACKTRACKING + DFS

     LOGIC:
     1. Start from every cell in the board because the first
        character of the word can be anywhere.
     2. If the current cell matches the current character of
        the word, explore its four directions:
        DOWN, UP, RIGHT, LEFT.
     3. Move to the next character using index + 1.
     4. Mark the current cell as visited so it cannot be reused
        in the same path.
     5. If a path fails, restore the cell and try another path.
     6. If all characters are matched, return true.

     THINGS TO REMEMBER:
     - index represents the character we need to match.
     - Never reuse a cell in the same path.
     - Mark the cell before exploring neighbors.
     - Restore the cell after exploring (backtracking).
     - No diagonal movement is allowed.
     - If index == word.length(), the whole word is found.
    */

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        // Try every cell as a starting point.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Start matching from index 0.
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        // No valid path found.
        return false;
    }

    private boolean dfs(char[][] board, String word,
                        int r, int c, int index) {

        // BASE CASE 1:
        // All characters have been matched.
        if (index == word.length()) {
            return true;
        }

        // BASE CASE 2:
        // Return false if out of bounds or the character
        // at the current cell does not match the word.
        if (r < 0 || c < 0 ||
            r >= board.length || c >= board[0].length ||
            board[r][c] != word.charAt(index)) {
            return false;
        }

        // Store the original character.
        // Mark the current cell as visited.
        char temp = board[r][c];
        board[r][c] = '#';

        // Explore all four directions.
        // index + 1 means we need to match the next character.
        boolean found =
                dfs(board, word, r + 1, c, index + 1) || // DOWN
                dfs(board, word, r - 1, c, index + 1) || // UP
                dfs(board, word, r, c + 1, index + 1) || // RIGHT
                dfs(board, word, r, c - 1, index + 1);  // LEFT

        // BACKTRACK:
        // Restore the original character so other paths
        // can use this cell.
        board[r][c] = temp;

        return found;
    }

    /*
     TIME COMPLEXITY:
     O(m * n * 3^L)

     m = number of rows
     n = number of columns
     L = length of the word

     We try every cell as a starting point.
     After the first move, there are at most 3 possible
     directions because we cannot reuse the previous cell.

     SPACE COMPLEXITY:
     O(L)

     The recursion stack can go as deep as the word length.
    */
}