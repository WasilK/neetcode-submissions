class Solution {
    public int mySqrt(int x) {
        int st = 1, en = x;
        while(st <= en){
            int mid = st + (en - st) / 2;
            long res = (long) mid * mid;
            if(res == x){
                return mid;
            }else if(res > x){
                en = mid - 1;
            }else{
                st = mid + 1;
            }
        }
        return en;
    }
}