class Solution {
    public int maxProduct(int[] nums) {
        int ans = nums[0];
        int maxP = nums[0];
        int minP = nums[0];

        for(int i = 1; i < nums.length; i++){
            int curr = nums[i];
            if(curr < 0){
                int temp = maxP;
                maxP = minP;
                minP = temp;
            }
            maxP = Math.max(curr * maxP, curr);
            minP = Math.min(curr * minP, curr);
            ans = Math.max(maxP, ans);
        }
        return ans;
    }
}
