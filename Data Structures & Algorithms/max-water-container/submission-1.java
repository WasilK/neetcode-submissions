class Solution {
    public int maxArea(int[] heights) {
        int l = 0, r = heights.length - 1;
        int mostWater = 0;
        while(l != r){
            int currWater = (r - l) * Math.min(heights[l], heights[r]);
            mostWater = Math.max(currWater, mostWater);
            if(heights[l] < heights[r]){
                l++;
            }else{
                r--;
            }
        }
        return mostWater;
    }
}
