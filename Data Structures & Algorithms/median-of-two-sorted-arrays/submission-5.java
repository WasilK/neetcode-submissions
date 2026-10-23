class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int A[] = nums1;
        int B[] = nums2;

        if(A.length > B.length){
            A = nums2;
            B = nums1;
        }

        int total = A.length + B.length;
        int half = (total + 1) / 2;

        int l = 0, r = A.length;
        while(l <= r){
            int i = (l + r) / 2;
            int j = half - i;
            
            int Aleft = i > 0 ? A[i - 1] : Integer.MIN_VALUE;
            int Aright = i < A.length ? A[i] : Integer.MAX_VALUE;
            int Bleft = j > 0 ? B[j - 1] : Integer.MIN_VALUE;
            int Bright = j < B.length ? B[j] : Integer.MAX_VALUE;

            if(Aleft <= Bright && Bleft <= Aright){
                if(total % 2 != 0){
                    return Math.max(Aleft, Bleft);
                }else{
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            }else if(Aleft > Bright){
                r = i - 1;
            }else{
                l = i + 1;
            }
        }
        return -1;
    }
}
