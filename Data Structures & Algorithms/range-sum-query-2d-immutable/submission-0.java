class NumMatrix {
    private int[][] sumMat;
    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        sumMat = new int[rows+1][cols+1];
        for(int i = 0; i < rows; i++){
            int prefix = 0;
            for(int j = 0; j < cols; j++){
                prefix += matrix[i][j];
                int above = sumMat[i][j+1];
                sumMat[i+1][j+1] = prefix + above;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++; col1++; row2++; col2++;
        int bottomRight = sumMat[row2][col2];
        int above = sumMat[row1-1][col2];
        int left = sumMat[row2][col1-1];
        int topLeft = sumMat[row1-1][col1-1];
        return bottomRight - above - left + topLeft;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */