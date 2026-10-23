class Solution {
    public int[] asteroidCollision(int[] asteroids) {

        // Stack stores asteroids that are currently alive.
        // We use the stack because a new asteroid can collide
        // only with the most recent asteroid moving in the opposite direction.
        Stack<Integer> s = new Stack<>();

        for (int a : asteroids) {

            // 'curr' represents the current asteroid.
            // We may set it to 0 if it gets destroyed during a collision.
            int curr = a;

            /*
             * Collision can happen only when:
             *
             * 1. Stack is not empty
             * 2. Current asteroid is moving LEFT  (curr < 0)
             * 3. Top of stack is moving RIGHT     (s.peek() > 0)
             *
             * Example:
             *      5  ->   <-  3
             *      stack   curr
             *
             * These two asteroids are moving towards each other.
             */
            while (!s.isEmpty() && curr < 0 && s.peek() > 0) {

                // Compare the sizes of the two colliding asteroids.
                //
                // Example:
                // stack top = 5
                // curr      = -3
                // diff = -3 + 5 = 2
                //
                // Positive -> stack asteroid survives
                // Negative -> current asteroid survives
                // Zero     -> both are destroyed
                int diff = curr + s.peek();

                if (diff > 0) {

                    // Stack asteroid is larger.
                    // Current asteroid is destroyed.
                    //
                    // Example: 5 and -3
                    // 5 survives, -3 is destroyed.
                    curr = 0;

                } else if (diff < 0) {

                    // Current asteroid is larger.
                    // Stack asteroid is destroyed.
                    //
                    // Example: 3 and -5
                    // 3 is removed from stack.
                    // -5 may still collide with another asteroid,
                    // so we DON'T set curr to 0.
                    s.pop();

                } else {

                    // Both asteroids have the same size.
                    // Therefore, both are destroyed.
                    //
                    // Example: 5 and -5
                    s.pop();
                    curr = 0;
                }
            }

            /*
             * If curr != 0, the current asteroid survived all
             * possible collisions, so we add it to the stack.
             *
             * Important:
             * We push 'a' because if curr is still non-zero,
             * it means the original asteroid survived.
             */
            if (curr != 0) {
                s.push(a);
            }
        }

        /*
         * Stack contains the surviving asteroids in their
         * original left-to-right order.
         *
         * But Stack.pop() gives elements from right to left.
         * So we fill the result array from the end.
         */
        int res[] = new int[s.size()];

        for (int i = s.size() - 1; i >= 0; i--) {
            res[i] = s.pop();
        }

        return res;
    }
}