class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int odd = 1, even = 1;
        int maxSize = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[i - 1]){
                odd = even + 1;
                even = 1;
            }else if(arr[i] < arr[i - 1]){
                even = odd + 1;
                odd = 1;
            }else{
                odd = 1;
                even = 1;
            }
            maxSize = Math.max(maxSize, Math.max(odd, even));
        }
        return maxSize;
    }
}