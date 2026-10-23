class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int res = 0;
        int st = 0, en = 0;
        for(int weight : weights){
            st = Math.max(st, weight);
            en += weight;
        }
        while(st <= en){
            int mid = (en + st) / 2;
            int daysNeeded = 1;
            int currentLoad = 0;
            for(int weight : weights){
                if(weight + currentLoad > mid){
                    daysNeeded++;
                    currentLoad = 0;
                }
                currentLoad += weight;
            }
            if(daysNeeded > days){
                st = mid + 1;
            }else{
                res = mid;
                en = mid - 1;
            }
        }
        return res;
    }
}