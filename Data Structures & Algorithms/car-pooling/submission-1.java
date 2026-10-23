class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int location[] = new int[1001];

        for(int trip[] : trips){
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            location[from] += passengers;
            location[to] -= passengers;
        }

        int passengers = 0;

        for(int i = 0; i < 1001; i++){
            passengers += location[i];
            if(passengers > capacity){
                return false;
            }
        }

        return true;
    }
}