class Solution {

    public int[] getOrder(int[][] tasks) {

        int n = tasks.length;

        /*
         * We create a new array because the original tasks array
         * only contains:
         *
         * [enqueueTime, processingTime]
         *
         * But we also need the original index of each task
         * for our final answer.
         *
         * So arr[i] will contain:
         *
         * [enqueueTime, processingTime, originalIndex]
         */
        int arr[][] = new int[n][3];


        // Add enqueue time, processing time and original index
        // of every task into arr.
        for(int i = 0; i < n; i++){

            arr[i][0] = tasks[i][0];  // enqueue time

            arr[i][1] = tasks[i][1];  // processing time

            arr[i][2] = i;            // original index
        }


        /*
         * Sort all tasks according to their enqueue time.
         *
         * Why?
         *
         * Because we want to process tasks in the order
         * in which they become available.
         *
         * arr[i][0] = enqueue time
         */
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));


        /*
         * Min Heap stores all the tasks which are CURRENTLY AVAILABLE.
         *
         * CPU chooses:
         *
         * 1. Task with smallest processing time.
         * 2. If processing time is same,
         *    task with smallest original index.
         *
         *
         * a[1] = processing time
         * a[2] = original index
         *
         *
         * Example:
         *
         * Available tasks:
         *
         * [enqueue, processing, index]
         *
         * [2, 5, 1]
         * [3, 2, 2]
         * [4, 2, 3]
         *
         * Heap chooses:
         *
         * [3, 2, 2]
         *
         * because processing time 2 is smallest.
         *
         * Task 2 and Task 3 both have processing time 2,
         * so smaller index (2) wins.
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] != b[1]
                    ? a[1] - b[1]     // smaller processing time first
                    : a[2] - b[2]     // if equal, smaller index first
        );


        // Stores the final order in which CPU executes tasks.
        int res[] = new int[n];


        // idx tells us where to insert the next task
        // in our result array.
        int idx = 0;


        /*
         * i points to the next task in the SORTED arr.
         *
         * Every time we put a task into the heap,
         * we increment i.
         */
        int i = 0;


        /*
         * Keeps track of current CPU time.
         *
         * Example:
         *
         * CPU starts at time 0.
         *
         * If a task takes 3 units:
         *
         * time = time + 3
         */
        int time = 0;


        /*
         * Continue until we have processed all n tasks.
         *
         * idx represents number of tasks already completed.
         */
        while(idx < n){


            /*
             * If PriorityQueue is empty,
             * there is currently NO task available for CPU.
             *
             * Example:
             *
             * current time = 2
             * next task arrives at time = 5
             *
             * Instead of waiting:
             *
             * 2 -> 3 -> 4 -> 5
             *
             * directly jump CPU time to 5.
             */
            if(pq.isEmpty() && time < arr[i][0]){

                time = arr[i][0];
            }


            /*
             * Add ALL tasks which have already arrived.
             *
             * A task is available when:
             *
             * enqueueTime <= currentTime
             *
             *
             * Example:
             *
             * current time = 5
             *
             * tasks:
             *
             * enqueue = 2  -> available
             * enqueue = 3  -> available
             * enqueue = 5  -> available
             * enqueue = 7  -> NOT available
             *
             * So first three tasks go into the heap.
             */
            while(i < n && arr[i][0] <= time){

                // Add available task into Min Heap.
                pq.offer(arr[i]);

                // Move to next task in sorted array.
                i++;
            }


            /*
             * Heap gives us the best available task.
             *
             * Priority:
             *
             * smallest processing time
             *
             * If tie:
             *
             * smallest original index
             */
            int task[] = pq.poll();


            /*
             * task[2] contains the ORIGINAL INDEX.
             *
             * Store it in our answer.
             *
             * idx++ means:
             *
             * store task index
             * and then move result pointer forward.
             */
            res[idx++] = task[2];


            /*
             * CPU executes the selected task.
             *
             * task[1] = processing time.
             *
             * Example:
             *
             * current time = 5
             * processing time = 3
             *
             * new time = 8
             */
            time += task[1];
        }


        // Return the order in which CPU processed the tasks.
        return res;
    }
}