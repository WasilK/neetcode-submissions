class Solution {
    public boolean isValidSudoku(char[][] board) {

        // Stores digits seen in each row.
        // Key = row number (0-8)
        // Value = HashSet of digits present in that row.
        HashMap<Integer, HashSet<Character>> rows = new HashMap<>();

        // Stores digits seen in each column.
        // Key = column number (0-8)
        // Value = HashSet of digits present in that column.
        HashMap<Integer, HashSet<Character>> cols = new HashMap<>();

        // Stores digits seen in each 3x3 box.
        // Key = "rowGroup,colGroup" (Example: "0,1")
        // Value = HashSet of digits present in that box.
        HashMap<String, HashSet<Character>> squares = new HashMap<>();


        // Traverse every cell of the Sudoku board.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                // Find which 3x3 box the current cell belongs to.
                //
                // Example:
                // Cell (4,7)
                // 4/3 = 1
                // 7/3 = 2
                // key = "1,2"
                //
                // Boxes are identified as:
                // 0,0   0,1   0,2
                // 1,0   1,1   1,2
                // 2,0   2,1   2,2
                String key = (i / 3) + "," + (j / 3);

                // Ignore empty cells.
                if (board[i][j] == '.') {
                    continue;
                }

                /*
                 * computeIfAbsent(key, ...)
                 *
                 * If the key already exists:
                 *      return the existing HashSet.
                 *
                 * If the key does not exist:
                 *      create a new HashSet,
                 *      store it in the map,
                 *      return that new HashSet.
                 *
                 * After getting the HashSet, contains() checks
                 * whether the current digit is already present.
                 *
                 * If the digit already exists in:
                 *   - the current row OR
                 *   - the current column OR
                 *   - the current 3x3 box
                 *
                 * then the Sudoku is invalid.
                 */
                if (rows.computeIfAbsent(i, k -> new HashSet<>()).contains(board[i][j]) ||
                    cols.computeIfAbsent(j, k -> new HashSet<>()).contains(board[i][j]) ||
                    squares.computeIfAbsent(key, k -> new HashSet<>()).contains(board[i][j])) {

                    return false;
                }

                // No duplicate found.
                // Add the current digit to:
                // 1. Row HashSet
                // 2. Column HashSet
                // 3. 3x3 Box HashSet
                rows.get(i).add(board[i][j]);
                cols.get(j).add(board[i][j]);
                squares.get(key).add(board[i][j]);
            }
        }

        // No duplicates found anywhere.
        return true;
    }
}