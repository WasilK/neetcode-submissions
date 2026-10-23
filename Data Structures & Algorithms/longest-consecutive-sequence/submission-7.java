class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int maxCount = 1;
        for(int val : set){
            int count = 1;
            while(set.contains(val + 1)){
                count++;
                val++;
                maxCount = Math.max(count, maxCount);
            }
        }
        return maxCount;
    }
}
