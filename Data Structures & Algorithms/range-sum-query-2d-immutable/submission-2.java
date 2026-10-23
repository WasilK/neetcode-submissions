class NumMatrix {

    // sumMatrix[i][j] stores the sum of the rectangle
    // from (0,0) to (i-1,j-1) in the original matrix.
    //
    // We make the matrix one row and one column larger so that
    // the first row and first column are all 0.
    // This avoids boundary checks while answering queries.
    int[][] sumMatrix;

    public NumMatrix(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        // Extra row and column filled with 0.
        sumMatrix = new int[row + 1][col + 1];

        // Build the 2D Prefix Sum Matrix.
        for (int i = 0; i < row; i++) {

            // Running sum of the current row.
            // Instead of recalculating the row sum again and again,
            // we keep adding the next element.
            //
            // Example:
            // Row = [3,0,1,4]
            //
            // prefix becomes:
            // 3 -> 3 -> 4 -> 8
            //
            // This makes construction O(rows * cols).
            int prefix = 0;

            for (int j = 0; j < col; j++) {

                // Current row prefix till column j.
                prefix += matrix[i][j];

                // Sum of everything above the current cell.
                //
                // Since sumMatrix already contains prefix sums
                // of previous rows, we simply reuse it.
                int above = sumMatrix[i][j + 1];

                // Current rectangle sum
                //
                // prefix = current row contribution
                // above  = contribution of previous rows
                //
                // Their sum gives the total rectangle
                // from (0,0) to (i,j).
                sumMatrix[i + 1][j + 1] = prefix + above;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        // Shift every coordinate by 1 because sumMatrix
        // has one extra row and column.
        row1++;
        col1++;
        row2++;
        col2++;

        // Entire rectangle from (0,0) to (row2,col2)
        int bottomRight = sumMatrix[row2][col2];

        // Remove everything above the required rectangle.
        int top = sumMatrix[row1 - 1][col2];

        // Remove everything left of the required rectangle.
        int left = sumMatrix[row2][col1 - 1];

        // The top-left part gets removed twice
        // (once in top and once in left),
        // so we add it back once.
        int topLeft = sumMatrix[row1 - 1][col1 - 1];

        // Inclusion-Exclusion Principle
        //
        // Wanted Rectangle =
        // Whole Rectangle
        // - Top Area
        // - Left Area
        // + Top-Left Overlap
        return bottomRight - top - left + topLeft;
    }
}

/*
========================= APPROACH =========================

Goal:
Answer multiple rectangle sum queries in O(1).

------------------------------------------------------------
Observation:
If we calculate every query by traversing the rectangle,
each query takes O(rows * cols).

Instead, preprocess the matrix once.

------------------------------------------------------------
Idea:

Create a Prefix Sum Matrix where

sumMatrix[i][j]

stores the sum of all elements from

(0,0) -----> (i-1,j-1)

Example:

Matrix

3 0 1
5 6 3

Prefix Matrix

0  0  0  0
0  3  3  4
0  8 14 18

18 means

3 + 0 + 1
5 + 6 + 3

------------------------------------------------------------
Construction Logic

For every row,

maintain a running row prefix.

prefix += matrix[i][j]

Then add the rectangle sum from previous rows.

Current Rectangle Sum =
Current Row Prefix
+
Above Rectangle Sum

sumMatrix[i+1][j+1]
=
prefix
+
sumMatrix[i][j+1]

------------------------------------------------------------
Query Logic

Suppose we want this rectangle.

+-----------------------+
|        Top            |
|-----------------------|
| Left | Wanted Area    |
| Left | Wanted Area    |
+-----------------------+

Start with the whole rectangle.

Subtract:
1. Top
2. Left

Now the Top-Left overlap has been removed twice.

So add it back once.

Formula:

Answer =
BottomRight
-
Top
-
Left
+
TopLeft

This is called the Inclusion-Exclusion Principle.

------------------------------------------------------------
Complexity

Construction : O(rows × cols)

Each Query   : O(1)

Space        : O(rows × cols)

------------------------------------------------------------
Things to Remember

1. Extra row and column remove boundary checks.

2. prefix stores the running sum of the current row.

3. above stores the contribution from previous rows.

4. bottomRight - top - left + topLeft
   because the overlap is subtracted twice.

If you remember these four points, you can reconstruct
the entire solution from scratch.
============================================================
*/