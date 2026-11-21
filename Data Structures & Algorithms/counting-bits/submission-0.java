class Solution {
    public int[] countBits(int n) {
        int arr[] = new int[n + 1];
        arr[0] = 0;
        for(int i = 1; i <= n; i++){
            int curr = i;
            int cnt = 0;
            while(curr != 0){
                curr = curr & (curr - 1);
                cnt++;
            }
            arr[i] = cnt;
        }
        return arr;
    }
}
