class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int longest = 0;
        for(int num : set){
            int len = 1;
            while(set.contains(num + len)){
                len++;
            }
            longest = Math.max(len, longest);
        }
        return longest;
    }
}
