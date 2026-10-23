class Solution {
    public int carFleet(int target, int[] position, int[] speed) {

        // Number of cars
        int n = position.length;

        /*
         * We need to keep the position and speed of each car together.
         *
         * pair[i][0] = position of car i
         * pair[i][1] = speed of car i
         *
         * Example:
         * position = [10, 8, 5]
         * speed    = [2,  4, 1]
         *
         * pair = [
         *          [10, 2],
         *          [ 8, 4],
         *          [ 5, 1]
         *        ]
         */
        int[][] pair = new int[n][2];


        /*
         * Combine position and speed into one 2D array.
         *
         * This is useful because we want to sort the cars
         * according to their positions while keeping their
         * corresponding speeds attached to them.
         */
        for (int i = 0; i < n; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }


        /*
         * Sort cars by POSITION in descending order.
         *
         * b[0] - a[0] means larger position comes first.
         *
         * Why descending?
         *
         * We want to process cars starting from the car
         * closest to the target and move backwards.
         *
         * Example:
         *
         * Before:
         * [5, 1]
         * [10, 2]
         * [3, 4]
         * [8, 2]
         *
         * After sorting:
         * [10, 2]  <- closest to target
         * [8,  2]
         * [5,  1]
         * [3,  4]
         *
         * This allows us to compare every car with the
         * fleet immediately ahead of it.
         */
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));


        /*
         * The car closest to the target will always form
         * at least one fleet.
         *
         * So initially we have 1 fleet.
         */
        int fleets = 1;


        /*
         * Calculate how much time the first car takes to
         * reach the target.
         *
         * Formula:
         *
         *       distance
         * time = --------
         *        speed
         *
         * distance = target - position
         *
         * We use (double) because division must produce a
         * decimal value.
         *
         * Example:
         *
         * target = 12
         * position = 10
         * speed = 2
         *
         * time = (12 - 10) / 2
         *      = 1 second
         *
         * prevTime represents the arrival time of the
         * fleet immediately ahead of the current car.
         */
        double prevTime =
                (double) (target - pair[0][0]) / pair[0][1];


        /*
         * Now process every car behind the first car.
         *
         * Remember:
         *
         * pair is sorted from closest → farthest
         *
         * So we are moving:
         *
         * FRONT OF ROAD → BACK OF ROAD
         *
         * Every current car can potentially catch
         * the fleet that is ahead of it.
         */
        for (int i = 1; i < n; i++) {


            /*
             * Calculate the time this car would take to
             * reach the target IF it could travel without
             * being slowed down by another car.
             *
             * Example:
             *
             * target = 12
             * position = 8
             * speed = 4
             *
             * time = (12 - 8) / 4
             *      = 1 second
             */
            double currTime =
                    (double) (target - pair[i][0]) / pair[i][1];


            /*
             * This is the MAIN LOGIC of the problem.
             *
             * We compare the current car's arrival time
             * with the fleet immediately ahead.
             *
             * ------------------------------------------------
             *
             * CASE 1:
             *
             * currTime <= prevTime
             *
             * The current car can reach the target in the
             * same time or earlier than the fleet ahead.
             *
             * Therefore, the current car will eventually
             * catch the fleet ahead.
             *
             * So:
             *
             *       current car
             *             ↓
             *       joins the fleet
             *
             * We DON'T create a new fleet.
             *
             * We also DON'T update prevTime because the
             * current car has become part of the fleet ahead.
             *
             *
             * ------------------------------------------------
             *
             * CASE 2:
             *
             * currTime > prevTime
             *
             * The current car would take MORE time to reach
             * the target than the fleet ahead.
             *
             * Therefore, it is too slow to catch that fleet.
             *
             * So it creates a NEW fleet.
             *
             * Example:
             *
             * prevTime = 3 seconds
             * currTime = 7 seconds
             *
             * The car behind needs 7 seconds while the fleet
             * ahead reaches in 3 seconds.
             *
             * It cannot catch the fleet.
             *
             * Therefore:
             *
             * fleets++;
             */
            if (currTime > prevTime) {

                // Current car cannot catch the fleet ahead,
                // so it forms a new fleet.
                fleets++;


                /*
                 * This new fleet is now the fleet immediately
                 * ahead of the cars that we will process next.
                 *
                 * Therefore, update prevTime.
                 */
                prevTime = currTime;
            }
        }


        /*
         * Return the total number of fleets.
         */
        return fleets;
    }
}