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
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals.isEmpty()){
            return 0;
        }
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Interval curr : intervals){
            if(!pq.isEmpty() && pq.peek() <= curr.start){
                pq.poll();
            }
            pq.offer(curr.end);
        }
        return pq.size();
    }
}
