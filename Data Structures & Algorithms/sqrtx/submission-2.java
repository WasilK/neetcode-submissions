class Solution {
    public int mySqrt(int x) {
        int st = 1, en = x;
        while(st <= en){
            int mid = st + (en - st) / 2;
            long n = (long) mid * mid;
            if(n == x){
                return mid;
            }else if(n > x){
                en = mid - 1;
            }else{
                st = mid + 1;
            }
        }
        return en;
    }
}