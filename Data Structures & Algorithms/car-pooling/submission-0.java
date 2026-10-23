class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        int passengers = 0;

        for(int trip[] : trips){
            int people = trip[0];
            int pickup = trip[1];
            int drop = trip[2];

            while(!pq.isEmpty() && pq.peek()[2] <= pickup){
                int finishedTrip[] = pq.poll();
                passengers -= finishedTrip[0];
            }

            passengers += people;

            if(passengers > capacity){
                return false;
            }

            pq.offer(trip);
        }
        return true; 
    }
}