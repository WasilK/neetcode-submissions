public class Solution {

    public int leastInterval(char[] tasks, int n) {

        // ---------------------------------------------------------
        // STEP 1: COUNT FREQUENCY OF EACH TASK
        // ---------------------------------------------------------
        //
        // There are only 26 possible tasks: A-Z.
        //
        // count[0] = number of A's
        // count[1] = number of B's
        // count[2] = number of C's
        // ...
        //
        // Example:
        // tasks = [A, A, B, B]
        //
        // count:
        // A -> 2
        // B -> 2
        //
        int[] count = new int[26];

        for (char task : tasks) {
            count[task - 'A']++;
        }


        // ---------------------------------------------------------
        // STEP 2: CREATE A MAX HEAP
        // ---------------------------------------------------------
        //
        // We want to execute the task that has the MOST remaining
        // occurrences first.
        //
        // Example:
        //
        // A -> 5
        // B -> 2
        // C -> 1
        //
        // Max Heap:
        // [5, 2, 1]
        //
        // poll() will always give us 5.
        //
        // WHY?
        // If a task occurs many times, we want to spread it out
        // as much as possible instead of leaving it until the end.
        //
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());


        // Put every non-zero frequency into the max heap.
        for (int cnt : count) {

            if (cnt > 0) {
                maxHeap.add(cnt);
            }
        }


        // ---------------------------------------------------------
        // STEP 3: CREATE A QUEUE FOR TASKS IN COOLDOWN
        // ---------------------------------------------------------
        //
        // A task cannot be executed again immediately.
        //
        // So after executing a task, we put it into this queue
        // until its cooldown period is over.
        //
        // Each element is:
        //
        // [remainingCount, availableTime]
        //
        // Example:
        //
        // [1, 4]
        //
        // means:
        //
        // "This task has 1 occurrence remaining and can be
        //  executed again at time 4."
        //
        Queue<int[]> q = new LinkedList<>();


        // ---------------------------------------------------------
        // STEP 4: START THE CLOCK
        // ---------------------------------------------------------
        //
        // time represents the current CPU time.
        //
        // We continue while either:
        //
        // 1. There are tasks available in the max heap
        // OR
        // 2. There are tasks waiting in cooldown
        //
        int time = 0;

        while (!maxHeap.isEmpty() || !q.isEmpty()) {

            // Move the clock forward by 1.
            time++;


            // -----------------------------------------------------
            // CASE 1: NO TASK IS CURRENTLY AVAILABLE
            // -----------------------------------------------------
            //
            // If the heap is empty, all remaining tasks are
            // currently cooling down.
            //
            // Instead of wasting time one unit at a time,
            // we jump directly to the next time a task becomes
            // available.
            //
            if (maxHeap.isEmpty()) {

                // q.peek()[1] = next available time
                //
                // Example:
                //
                // q = [[1, 5]]
                //
                // Nothing can execute until time 5.
                //
                // So jump directly to time 5.
                //
                time = q.peek()[1];


            } else {

                // -------------------------------------------------
                // CASE 2: A TASK IS AVAILABLE
                // -------------------------------------------------
                //
                // Take the task with the highest remaining count.
                //
                // Example:
                //
                // heap = [3, 2, 1]
                //
                // poll() gives 3.
                //
                int cnt = maxHeap.poll() - 1;


                // We have executed ONE occurrence of that task.
                //
                // Example:
                //
                // Before: A has 3 remaining
                // Execute A
                // After:  A has 2 remaining
                //
                // If there are still occurrences left,
                // this task must enter cooldown.
                //
                if (cnt > 0) {

                    // Store:
                    //
                    // cnt       = remaining occurrences
                    // time + n  = when it can become available again
                    //
                    // Example:
                    //
                    // time = 2
                    // n = 2
                    //
                    // Put [1, 4] into queue.
                    //
                    // Meaning:
                    //
                    // "1 occurrence remains and this task
                    //  can return at time 4."
                    //
                    q.add(new int[]{cnt, time + n});
                }
            }


            // -----------------------------------------------------
            // STEP 5: CHECK WHETHER A TASK'S COOLDOWN IS OVER
            // -----------------------------------------------------
            //
            // The queue is ordered by available time.
            //
            // Look at the task at the front.
            //
            // If its available time == current time,
            // its cooldown is finished.
            //
            if (!q.isEmpty() && q.peek()[1] == time) {

                // Remove the task from cooldown...
                //
                // q.poll()[0] = remaining count
                //
                // ...and put it back into the max heap.
                //
                // Now it is available to execute again.
                //
                maxHeap.add(q.poll()[0]);
            }
        }


        // ---------------------------------------------------------
        // ALL TASKS ARE FINISHED
        // ---------------------------------------------------------
        //
        // 'time' represents the total amount of CPU time used,
        // including idle time.
        //
        return time;
    }
}

