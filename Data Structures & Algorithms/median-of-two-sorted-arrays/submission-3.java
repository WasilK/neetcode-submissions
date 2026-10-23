class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int A[] = nums1;
        int B[] = nums2;
        if(A.length > B.length){
            int temp[] = A;
            A = B;
            B = temp;
        }
        int total = A.length + B.length;
        int half = (total + 1) / 2;
        int l = 0, r = A.length;
        while(l <= r){
            int i = (l + r) / 2;
            int j = half - i;

            int leftA = i > 0 ? A[i - 1] : Integer.MIN_VALUE;
            int rightA = i < A.length ? A[i] : Integer.MAX_VALUE;
            int leftB = j > 0 ? B[j - 1] : Integer.MIN_VALUE;
            int rightB = j < B.length ? B[j] : Integer.MAX_VALUE;

            if(leftA <= rightB && leftB <= rightA){
                if(total % 2 != 0){
                    return Math.max(leftA, leftB);
                }else{
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
            }else{
                if(leftA > rightB){
                    r = i - 1;
                }else{
                    l = i + 1;
                }
            }
        }
        return -1;
    }
}
