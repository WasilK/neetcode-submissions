public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // APPROACH:
        // The matrix is sorted row-wise, and the first element of each row
        // is greater than the last element of the previous row.
        //
        // Therefore, we can imagine the entire 2D matrix as a sorted 1D array.
        //
        // Example:
        //
        // Matrix:
        // [ 1,  3,  5,  7 ]
        // [ 10, 11, 16, 20 ]
        // [ 23, 30, 34, 60 ]
        //
        // Imagine it as:
        // [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
        //
        // Then we can simply apply Binary Search.
        
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        // Since there are ROWS * COLS total elements,
        // the last index in our imaginary 1D array is:
        //
        // ROWS * COLS - 1
        //
        // Example: 3 rows * 4 columns = 12 elements
        // Therefore indexes are 0 to 11.
        
        int l = 0;
        int r = ROWS * COLS - 1;

        while (l <= r) {

            // Find the middle index of our imaginary 1D array.
            int m = l + (r - l) / 2;

            // Now we have a 1D index 'm', but our actual data
            // is stored in a 2D matrix.
            //
            // We need to convert:
            //
            //     1D index m
            //          ↓
            //     2D index [row][col]
            //
            // Formula:
            //
            // row = m / COLS
            // col = m % COLS
            //
            // Why?
            //
            // Every row contains COLS elements.
            //
            // For example, if COLS = 4:
            //
            // 1D indexes:
            // 0  1  2  3  → row 0
            // 4  5  6  7  → row 1
            // 8  9 10 11  → row 2
            //
            // m / COLS tells us WHICH ROW.
            //
            // Example:
            // m = 6
            // 6 / 4 = 1
            // So row = 1.
            //
            // m % COLS tells us WHICH COLUMN inside that row.
            //
            // Example:
            // m = 6
            // 6 % 4 = 2
            // So col = 2.
            //
            // Therefore:
            // m = 6 → matrix[1][2]
            
            int row = m / COLS;
            int col = m % COLS;

            // Compare the target with the middle element.
            
            if (target > matrix[row][col]) {

                // Target is greater than the middle element.
                // Therefore, search in the right half.
                
                l = m + 1;

            } else if (target < matrix[row][col]) {

                // Target is smaller than the middle element.
                // Therefore, search in the left half.
                
                r = m - 1;

            } else {

                // Target is equal to the middle element.
                // Therefore, we found the target.
                
                return true;
            }
        }

        // If the binary search finishes without finding the target,
        // the target does not exist in the matrix.
        
        return false;
    }
}