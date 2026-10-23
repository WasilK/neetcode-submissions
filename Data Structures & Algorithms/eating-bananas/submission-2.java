class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int st = 1, en = piles[piles.length - 1];
        int res = 1;
        while(st <= en){
            int mid = st + (en - st) / 2;
            int totalTime = 0;
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
