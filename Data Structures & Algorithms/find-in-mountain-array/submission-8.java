/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int peak = 0;
        int st = 1, en = len - 2;
        while(st <= en){
            int m = (st + en) / 2;
            int left = mountainArr.get(m - 1);
            int mid = mountainArr.get(m);
            int right = mountainArr.get(m + 1);

            if(left < mid && mid < right){
                st = m + 1;
            }else if(left > mid && mid > right){
                en = m - 1;
            }else{
                peak = m;
                break;
            }
        }

        // left part array
        st = 0;
        en = peak - 1;
        while(st <= en){
            int m = (st + en) / 2;
            if(mountainArr.get(m) == target){
                return m;
            }else if(mountainArr.get(m) > target){
                en = m - 1;
            }else{
                st = m + 1;
            }
        }

        //right part array
        st = peak; 
        en = len - 1;
        while(st <= en){
            int m = (st + en) / 2;
            if(mountainArr.get(m) == target){
                return m;
            }else if(mountainArr.get(m) > target){
                st = m + 1;
            }else{
                en = m - 1;
            }
        }
        return -1;
    }
}