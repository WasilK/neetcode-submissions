class Solution {
    public boolean carPooling(int[][] trips, int capacity) {

        // location[i] tells us the CHANGE in the number of
        // passengers at location/time i.
        //
        // Example:
        // [2, 1, 5]
        //
        // 2 passengers get ON at location 1
        // 2 passengers get OFF at location 5
        //
        // So:
        //
        // location[1] += 2
        // location[5] -= 2
        //
        // We use size 1001 because the locations are
        // between 0 and 1000.

        int location[] = new int[1001];


        // Process every trip.

        for(int trip[] : trips) {

            // trip[0] = number of passengers
            // trip[1] = pickup location
            // trip[2] = drop-off location

            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];


            // At 'from', these passengers ENTER the car.
            //
            // Therefore, increase the passenger count.

            location[from] += passengers;


            // At 'to', these passengers LEAVE the car.
            //
            // Therefore, decrease the passenger count.
            //
            // We don't remove them immediately.
            // We simply record that at this location
            // the passenger count should decrease.

            location[to] -= passengers;
        }


        // This will store the ACTUAL number of passengers
        // currently inside the car as we move through
        // the locations.

        int passengers = 0;


        // Now move from location 0 → 1000.
        //
        // This is basically simulating the car moving
        // through every location.

        for(int i = 0; i < 1001; i++) {

            // Apply the change that happens at this location.
            //
            // Positive value -> passengers get ON
            // Negative value -> passengers get OFF
            //
            // Example:
            //
            // passengers = 2
            // location[i] = +3
            //
            // passengers becomes 5.

            passengers += location[i];


            // At every location we MUST ensure that
            // the car's capacity is not exceeded.
            //
            // If:
            //
            // passengers > capacity
            //
            // then there are too many passengers in the car.

            if(passengers > capacity) {
                return false;
            }
        }


        // We travelled through every location without
        // exceeding the capacity.
        //
        // Therefore, the trips can be completed.

        return true;
    }
}