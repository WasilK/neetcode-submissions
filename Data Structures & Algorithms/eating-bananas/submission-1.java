class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int st = 1;
        int en = Arrays.stream(piles).max().getAsInt();
        int res = 1;
        while(st <= en){
            int mid = (st + en) / 2;
            long totalTime = 0;
            for(int pile : piles){
                totalTime += (int) Math.ceil((double) pile / mid);
            }
            if(totalTime <= h){
                res = mid;
                en = mid - 1;
            }else{
                st = mid + 1;
            }
        }
        return res;
    }
}
