class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int res[] = merge(nums1, nums1.length, nums2, nums2.length);
        int st = 0, en = res.length - 1;
        int mid = (st + en) / 2;
        if(res.length % 2 != 0){
            return (double) res[mid];
        }else{
            return (double) (res[mid] + res[mid + 1]) / 2;
        }
    }
    public int[] merge(int nums1[], int m, int nums2[], int n){
        int res[] = new int[m + n];
        int i = 0, j = 0, k = 0;
        while(i < m && j < n){
            if(nums1[i] < nums2[j]){
                res[k++] = nums1[i++];
            }else{
                res[k++] = nums2[j++];
            }
        }
        while(i < m){
            res[k++] = nums1[i++];
        }
        while(j < n){
            res[k++] = nums2[j++];
        }
        return res;
    }
}
