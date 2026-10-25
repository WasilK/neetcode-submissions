class Solution {

    // Stores all valid N-Queens board configurations.
    // Each solution is a List<String>, and all solutions are stored
    // inside this outer list.
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        // STEP 1: Create an n x n chessboard.
        // Initially, every cell is empty and represented by '.'.
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // STEP 2: Create arrays to track occupied columns and diagonals.
        //
        // cols[col] = true means a queen already occupies this column.
        //
        // An n x n board has (2*n - 1) diagonals in each direction.
        // Therefore, both diagonal arrays have length 2*n - 1.
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];

        // STEP 3: Start placing queens from row 0.
        // We place exactly one queen in each row.
        backtrack(board, 0, n, cols, diag1, diag2);

        return res;
    }

    void backtrack(
            char[][] board,
            int row,
            int n,
            boolean[] cols,
            boolean[] diag1,
            boolean[] diag2) {

        // BASE CASE:
        // If row == n, we have placed one queen in every row.
        // Because we only place queens in safe positions,
        // this board is a complete valid solution.
        if (row == n) {

            // Convert each char[] row into a String.
            // Example: {'.', 'Q', '.', '.'} becomes ".Q.."
            List<String> curr = new ArrayList<>();

            for (char[] r : board) {
                curr.add(new String(r));
            }

            // Save this complete solution.
            // curr is a separate list containing the board's strings.
            res.add(curr);
            return;
        }

        // Try placing a queen in every column of the current row.
        // We must start from column 0 because any column may be valid.
        for (int col = 0; col < n; col++) {

            // DIAGONAL FORMULA 1: row - col + n - 1
            //
            // Cells on the same top-left-to-bottom-right diagonal
            // have the same value of (row - col).
            //
            // However, row - col can be negative.
            // Its minimum possible value is -(n - 1).
            // Adding (n - 1) shifts every index into [0, 2*n - 2].
            //
            // Example: n = 4, row = 1, col = 2
            // d1 = 1 - 2 + 4 - 1 = 2
            int d1 = row - col + n - 1;

            // DIAGONAL FORMULA 2: row + col
            //
            // Cells on the same top-right-to-bottom-left diagonal
            // have the same value of (row + col).
            //
            // Its minimum is 0 and maximum is 2*n - 2,
            // so it directly fits into an array of length 2*n - 1.
            //
            // Example: n = 4, row = 1, col = 2
            // d2 = 1 + 2 = 3
            int d2 = row + col;

            // CHECK WHETHER THIS POSITION IS SAFE.
            //
            // If the column or either diagonal is occupied,
            // placing a queen here would cause a conflict.
            //
            // Boolean-array lookups take O(1) time.
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // CHOOSE:
            // Place a queen at the current position.
            board[row][col] = 'Q';

            // Mark its column and both diagonals as occupied.
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // EXPLORE:
            // Move to the next row to place the next queen.
            backtrack(board, row + 1, n, cols, diag1, diag2);

            // UNCHOOSE (BACKTRACK):
            // Remove the queen and undo all tracking changes.
            //
            // This is essential because we must try other columns
            // in the current row without retaining the old placement.
            board[row][col] = '.';

            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}

/*
APPROACH: BACKTRACKING

1. Start at row 0.
2. Try every column in the current row.
3. Check whether the column and both diagonals are free.
4. If safe, place the queen and mark the occupied positions.
5. Recursively solve the next row.
6. Undo the placement and tracking changes.
7. When row == n, save the complete board as one solution.

WHY ONE QUEEN PER ROW?
A valid N-Queens solution requires that no two queens share
a row. Placing exactly one queen per row guarantees this rule,
so we only need to check columns and diagonals.

TIME COMPLEXITY:
O(N! + S*N), commonly expressed as O(N!) when discussing
the backtracking search, where S is the number of solutions.

More precisely:
- The search explores partial placements of queens.
- Because columns cannot repeat, the number of possible
  placement sequences is bounded by permutations, up to N!.
- Each candidate position has O(1) conflict-checking cost.
- Constructing and storing each complete solution costs O(N).
- Therefore, the output-generation cost is O(S*N).

SPACE COMPLEXITY:
O(N^2) auxiliary space for the chessboard.
O(N) for the recursion stack, column array, and diagonal arrays.
The total auxiliary space is O(N^2), excluding the result list.

The result list requires O(S*N) space because every solution
contains N strings, each representing a row of length N.

NOTE:
If solveNQueens() is called multiple times on the same Solution
object, clear res at the start of that method to avoid retaining
solutions from an earlier call.
*/
