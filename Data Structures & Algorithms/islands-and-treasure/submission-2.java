class Solution {

    public void islandsAndTreasure(int[][] grid) {

        /*
         * APPROACH: MULTI-SOURCE BFS
         *
         * We need to find the shortest distance from every empty room
         * to its nearest treasure (cell containing 0).
         *
         * Instead of starting BFS separately from every empty room,
         * we start BFS simultaneously from ALL treasure cells.
         *
         * Why?
         * BFS explores cells level by level.
         * The first treasure to reach an empty room gives its
         * shortest distance to any treasure.
         *
         * Grid values:
         * 0                   -> Treasure
         * -1                  -> Wall
         * Integer.MAX_VALUE   -> Empty, unvisited room
         */

        // Number of rows and columns in the grid
        int m = grid.length;
        int n = grid[0].length;

        /*
         * Create a queue to store coordinates of cells.
         *
         * int[] stores two values:
         * int[0] -> Row index
         * int[1] -> Column index
         *
         * Example:
         * new int[]{1, 2} represents cell (1, 2).
         *
         * Queue follows FIFO:
         * First In, First Out.
         *
         * This is important for BFS because we process
         * cells in increasing order of distance.
         */
        Queue<int[]> q = new LinkedList<>();


        /*
         * STEP 1: FIND ALL TREASURES
         *
         * Traverse the entire grid.
         * Whenever we find a cell containing 0,
         * add its coordinates to the queue.
         *
         * We add ALL treasures before starting BFS.
         */

        for (int i = 0; i < m; i++) {

            // Traverse every column of the current row
            for (int j = 0; j < n; j++) {

                // Check if the current cell is a treasure
                if (grid[i][j] == 0) {

                    // Add treasure coordinates to the queue
                    q.add(new int[]{i, j});
                }
            }
        }


        /*
         * STEP 2: DEFINE FOUR DIRECTIONS
         *
         * Each inner array represents a movement.
         *
         * {-1, 0} -> Up
         * { 1, 0} -> Down
         * { 0,-1} -> Left
         * { 0, 1} -> Right
         *
         * For a current cell (i, j):
         *
         * New row    = i + dir[0]
         * New column = j + dir[1]
         *
         * This avoids writing separate code for
         * all four neighboring cells.
         */

        int directions[][] = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };


        /*
         * STEP 3: PERFORM BFS
         *
         * Continue until the queue becomes empty.
         *
         * Every cell in the queue represents a cell
         * whose neighbors still need to be explored.
         *
         * When the queue becomes empty, all reachable
         * empty rooms have received their distances.
         */

        while (!q.isEmpty()) {

            /*
             * Remove the front cell from the queue.
             *
             * poll() removes and returns the first element.
             *
             * Example:
             * If the queue contains [(0,2), (1,3)],
             * current will contain (0,2).
             */
            int curr[] = q.remove();


            /*
             * Extract the coordinates of the current cell.
             *
             * curr[0] -> Row
             * curr[1] -> Column
             */
            int i = curr[0];
            int j = curr[1];


            /*
             * STEP 4: EXPLORE ALL FOUR NEIGHBORS
             *
             * Enhanced for loop:
             *
             * for (int[] dir : directions)
             *
             * Means:
             * Take each direction array one by one.
             *
             * Example:
             * First iteration:  dir = {-1, 0}
             * Second iteration: dir = {1, 0}
             * Third iteration:  dir = {0, -1}
             * Fourth iteration: dir = {0, 1}
             */

            for (int dir[] : directions) {

                /*
                 * Calculate the neighboring cell coordinates.
                 *
                 * ni -> Neighbor row index
                 * nj -> Neighbor column index
                 *
                 * Example:
                 * Current cell = (2, 3)
                 * Direction    = {1, 0}
                 *
                 * ni = 2 + 1 = 3
                 * nj = 3 + 0 = 3
                 *
                 * Neighbor = (3, 3)
                 */
                int ni = dir[0] + i;
                int nj = dir[1] + j;


                /*
                 * STEP 5: CHECK GRID BOUNDARIES
                 *
                 * A valid cell must satisfy:
                 *
                 * 0 <= ni < m
                 * 0 <= nj < n
                 *
                 * If any condition fails, the neighbor
                 * is outside the grid.
                 *
                 * continue skips the current direction
                 * and moves to the next direction.
                 */

                if (ni < 0 || nj < 0 ||
                    ni >= m || nj >= n) {

                    continue;
                }


                /*
                 * STEP 6: CHECK WHETHER THE NEIGHBOR
                 * IS AN UNVISITED EMPTY ROOM
                 *
                 * Integer.MAX_VALUE represents INF.
                 *
                 * We only process cells containing INF.
                 *
                 * Why?
                 *
                 * 1. Walls contain -1.
                 *    We must not move through walls.
                 *
                 * 2. Treasure cells contain 0.
                 *    They already have distance 0.
                 *
                 * 3. Visited empty rooms contain a distance.
                 *    We must not process them again.
                 *
                 * Once an INF cell is updated, it is no
                 * longer INF. Therefore, we don't need
                 * a separate visited[][] array.
                 */

                if (grid[ni][nj] == Integer.MAX_VALUE) {


                    /*
                     * STEP 7: UPDATE THE DISTANCE
                     *
                     * The neighboring cell is exactly
                     * one step farther from the treasure
                     * than the current cell.
                     *
                     * Formula:
                     *
                     * Distance of neighbor =
                     * Distance of current cell + 1
                     *
                     * Example:
                     * Current cell distance = 2
                     * Neighbor distance      = 3
                     *
                     * BFS guarantees this is the shortest
                     * distance because cells are explored
                     * level by level.
                     */

                    grid[ni][nj] = grid[i][j] + 1;


                    /*
                     * STEP 8: ADD THE UPDATED CELL
                     * TO THE QUEUE
                     *
                     * Why?
                     *
                     * The newly updated cell may have
                     * other unvisited neighbors.
                     *
                     * We need to explore those neighbors
                     * in future BFS iterations.
                     *
                     * Therefore, we add its coordinates
                     * to the queue.
                     */

                    q.add(new int[]{ni, nj});
                }
            }
        }


        /*
         * BFS IS COMPLETE.
         *
         * Every reachable empty room now contains
         * its shortest distance to the nearest treasure.
         *
         * Walls remain -1.
         * Treasure cells remain 0.
         *
         * The original grid is modified directly.
         * No return statement is required.
         *
         * TIME COMPLEXITY:
         * O(m * n)
         *
         * Every cell is processed at most once.
         * Each cell checks four neighbors.
         *
         * SPACE COMPLEXITY:
         * O(m * n)
         *
         * In the worst case, the queue can contain
         * O(m * n) cells.
         */
    }
}
