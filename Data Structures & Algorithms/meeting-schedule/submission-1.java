/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        int n = intervals.size();
        if(n == 1 || n == 0){
            return true;
        }
        int arr[][] = new int[n][3];
        for(int i = 0; i < n; i++){
            Interval interval = intervals.get(i);
            arr[i][0] = interval.start;
            arr[i][1] = interval.end;
            arr[i][2] = i;
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));
        int lastTime = arr[0][1];
        for(int i = 1; i < n; i++){
            if(arr[i][0] < lastTime){
                return false;
            }
            lastTime = arr[i][1];
        }
        return true;
    }
}
