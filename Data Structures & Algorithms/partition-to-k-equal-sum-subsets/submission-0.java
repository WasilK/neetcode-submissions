class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0) return false;
        int length = sum / k;
        int subsets[] = new int[k];
        Arrays.sort(nums);
        reverse(nums);
        return backtrack(nums, subsets, 0, length);
    }

    boolean backtrack(int nums[], int subsets[], int idx, int length){
        if(idx == nums.length){
            return true;
        }
        for(int i = 0; i < subsets.length; i++){
            if(subsets[i] + nums[idx] <= length){
                subsets[i] += nums[idx];
                if(backtrack(nums, subsets, idx + 1, length)) return true;
                subsets[i] -= nums[idx];
            }
            if(subsets[i] == 0) break;
        }
        return false;
    }

    void reverse(int nums[]){
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}