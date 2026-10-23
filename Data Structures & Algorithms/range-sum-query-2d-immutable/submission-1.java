class NumMatrix {
    int sumMatrix[][];
    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        sumMatrix = new int[row + 1][col + 1];

        for(int i = 0; i < row; i++){
            int prefix = 0;
            for(int j = 0; j < col; j++){
                prefix += matrix[i][j];
                int above = sumMatrix[i][j + 1];
                sumMatrix[i + 1][j + 1] = prefix + above;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++; col1++; row2++; col2++;
        int bottomRight = sumMatrix[row2][col2];
        int top = sumMatrix[row1 - 1][col2];
        int left = sumMatrix[row2][col1 - 1];
        int topLeft = sumMatrix[row1 - 1][col1 - 1];
        return bottomRight - top - left + topLeft;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */