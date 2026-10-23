class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int st = 0, en = 0;
        for(int weight: weights){
            st = Math.max(st, weight);
            en += weight;
        }
        int res = 0;
        while(st <= en){
            int mid = (st + en) / 2;
            int daysNeeded = 1;
            int currentLoad = 0;
            for(int weight : weights){
                if(currentLoad + weight > mid){
                    daysNeeded++;
                    currentLoad = 0;
                }
                currentLoad += weight;
            }
            if(daysNeeded <= days){
                res = mid;
                en = mid - 1;
            }else{
                st = mid + 1;
            }
        }
        return res;
    }
}