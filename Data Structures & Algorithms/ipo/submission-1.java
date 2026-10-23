class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        /*
         * We have two PriorityQueues:
         *
         * 1. minCapital:
         *    Stores project INDEXES.
         *    The project requiring the least capital comes first.
         *
         *    Example:
         *    capital = [0, 1, 1]
         *    indexes = [0, 1, 2]
         *
         *    minCapital will give us:
         *    project 0 -> capital required = 0
         *    project 1 -> capital required = 1
         *    project 2 -> capital required = 1
         *
         *
         * 2. maxProfit:
         *    Also stores project INDEXES.
         *    Among the projects that we can currently afford,
         *    it gives us the project with the highest profit.
         */

        PriorityQueue<Integer> minCapital =
                new PriorityQueue<>((a, b) -> capital[a] - capital[b]);

        PriorityQueue<Integer> maxProfit =
                new PriorityQueue<>((a, b) -> profits[b] - profits[a]);


        /*
         * Put every project INDEX into minCapital.
         *
         * We store the index instead of the actual capital/profit
         * because we need to access BOTH arrays using the same index.
         *
         * Example:
         *
         * profits = [1, 2, 3]
         * capital = [0, 1, 1]
         *
         * We insert:
         * 0, 1, 2
         *
         * The minCapital heap sorts them according to:
         * capital[index]
         */
        for (int i = 0; i < capital.length; i++) {
            minCapital.offer(i);
        }


        /*
         * We can select at most k projects.
         *
         * Each iteration represents selecting ONE project.
         */
        for (int i = 0; i < k; i++) {

            /*
             * Move every currently affordable project
             * from minCapital -> maxProfit.
             *
             * capital[minCapital.peek()] gives the capital
             * required by the cheapest remaining project.
             *
             * If that capital <= our current money (w),
             * we can afford that project.
             *
             * We keep doing this until there are no more
             * affordable projects.
             */
            while (!minCapital.isEmpty()
                    && capital[minCapital.peek()] <= w) {

                /*
                 * poll() removes the cheapest affordable project
                 * from minCapital.
                 *
                 * Then we put its index into maxProfit.
                 */
                maxProfit.offer(minCapital.poll());
            }


            /*
             * If maxProfit is empty, it means:
             *
             * There is no project that we can currently afford.
             *
             * Therefore, we cannot continue.
             */
            if (maxProfit.isEmpty()) {
                break;
            }


            /*
             * maxProfit gives us the project with the
             * HIGHEST PROFIT among all affordable projects.
             *
             * Add that project's profit to our current capital.
             *
             * This is the greedy choice:
             *
             * "Among all projects I can afford,
             *  choose the one that gives the maximum profit."
             */
            w += profits[maxProfit.poll()];
        }


        /*
         * After selecting at most k projects,
         * return the maximum capital we have.
         */
        return w;
    }
}