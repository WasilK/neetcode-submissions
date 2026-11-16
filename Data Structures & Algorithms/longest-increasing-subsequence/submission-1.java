class Solution {
    public int lengthOfLIS(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int arr[] = new int[set.size()];
        int i = 0;
        for(int s : set){
            arr[i] = s;
            i++;
        }
        Arrays.sort(arr);
        return lcs(nums, arr);
    }
    int lcs(int arr1[], int arr2[]){
        int n = arr1.length; 
        int m = arr2.length;
        int dp[][] = new int[n + 1][m + 1];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < m + 1; j++){
                if(arr1[i - 1] == arr2[j - 1]){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
