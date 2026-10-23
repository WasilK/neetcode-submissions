

public class Solution {
    /**
     * Finds the area of the largest rectangle in a histogram.
     * 
     * APPROACH: Monotonic Increasing Stack
     * ------------------------------------
     * 1. Iterate through each bar and treat its height as the MINIMUM height 
     *    of a potential rectangle.
     * 2. Use a stack to keep track of bar indices in strictly increasing order 
     *    of heights.
     * 3. When we encounter a bar shorter than the bar at stack top, we calculate 
     *    the maximum rectangle area possible for the taller bar.
     *
     * TIME COMPLEXITY  : O(n) - Every index is pushed and popped at most once.
     * SPACE COMPLEXITY : O(n) - Stack stores up to n indices.
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Monotonic Stack: Stores INDICES of bars with strictly increasing heights.
        Stack<Integer> stack = new Stack<>();

        // ---------------------------------------------------------------------
        // VIRTUAL SENTINEL TRICK (i <= n):
        // Running the loop up to i = n introduces an implicit "guard bar" of height 0 
        // at the end of the histogram. This forces ALL remaining elements in 
        // the stack to pop and get evaluated before the loop terminates.
        // ---------------------------------------------------------------------
        for (int i = 0; i <= n; i++) {

            // WHILE LOOP CONDITION:
            // Process stack elements if:
            //   1. Stack is NOT empty, AND
            //   2. (i == n) -> Virtual sentinel at the end, forces a complete cleanup.
            //      OR
            //      heights[stack.peek()] >= heights[i] -> The bar at stack top 
            //      CANNOT extend any further to the right because bar 'i' is shorter.
            // 
            // Note: Java uses short-circuit evaluation for '||'. When i == n, 
            // heights[i] is NEVER evaluated, avoiding Out-Of-Bounds exceptions.
            while (!stack.isEmpty() && 
                   (i == n || heights[stack.peek()] >= heights[i])) {

                // 1. POP HEIGHT:
                // Pop the top bar index and use its height as the rectangle height.
                int height = heights[stack.pop()];

                // 2. COMPUTE WIDTH:
                // Right Boundary (R) = i - 1 
                //   (Bar 'i' is the first bar to the right strictly shorter than 'height').
                // 
                // Left Boundary (L):
                //   - CASE A: Stack is NOT empty after pop:
                //     The new stack top is the first bar to the left shorter than 'height'.
                //     So L = stack.peek() + 1
                //     Width = R - L + 1 
                //           = (i - 1) - (stack.peek() + 1) + 1 
                //           = i - stack.peek() - 1
                //
                //   - CASE B: Stack IS empty after pop:
                //     There are no shorter bars to the left; 'height' extends back to index 0.
                //     So L = 0
                //     Width = R - L + 1 
                //           = (i - 1) - 0 + 1 
                //           = i
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                // 3. UPDATE MAXIMUM AREA:
                maxArea = Math.max(maxArea, height * width);
            }

            // Push current index 'i' to preserve the monotonic increasing order.
            stack.push(i);
        }

        return maxArea;
    }
}