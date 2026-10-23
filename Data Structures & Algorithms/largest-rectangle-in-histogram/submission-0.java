class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;

        for(int i = 0; i <= n; i++){
            while(!s.isEmpty() && ((i == n) || heights[s.peek()] >= heights[i])){
                int height = heights[s.pop()];
                int width = s.isEmpty() ? i : i - s.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            s.push(i);
        }
        return maxArea;
    }
}
