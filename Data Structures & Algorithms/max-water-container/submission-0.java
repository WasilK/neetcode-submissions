class Solution {
    public int maxArea(int[] heights) {
       int lp = 0, rp = heights.length - 1;
       int mostWater = 0;
       while(lp != rp){
        int width = rp - lp;
        int height = Math.min(heights[lp], heights[rp]);
        int water = width * height;
        mostWater = Math.max(mostWater, water);
        if(heights[lp] < heights[rp]){
            lp++;
        }else{
            rp--;
        }
       }
       return mostWater;
    }
}
