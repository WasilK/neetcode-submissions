class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int st = 0, en = (rows * cols) - 1;
        while(st <= en){
            int mid = st + (en - st) / 2;
            int row = mid / cols, col = mid % cols;
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] > target){
                en = mid - 1;
            }else{
                st = mid + 1;
            }
        }
        return false;
    }
}
