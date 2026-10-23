class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int prev = 0, curr = 0, count = 0;
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        while(count < (m + n) / 2 + 1){
            if(i < m && j < n){
                if(nums1[i] < nums2[j]){
                prev = curr;
                curr = nums1[i++];
                count++;
            }else{
                prev = curr;
                curr = nums2[j++];
                count++;
            }
            }else if(i < m){
                prev = curr;
                curr = nums1[i++];
                count++;
            }else if(j < n){
                prev = curr;
                curr = nums2[j++];
                count++;
            }
        }
        if((m + n) % 2 == 0){
            return (prev + curr) / 2.0;
        }else{
            return (double) curr;
        }
    }
}
