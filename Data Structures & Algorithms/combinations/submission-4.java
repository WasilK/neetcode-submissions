
class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        // Start building combinations from number 1.
        // curr stores the combination we are currently building.
        res.clear();
        backtrack(n, 1, k, new ArrayList<>());

        return res;
    }

    public void backtrack(int n, int start, int k,
                          List<Integer> curr) {

        // BASE CASE:
        // If curr contains k numbers, we have found
        // one valid combination.
        if (curr.size() == k) {

            // Store a COPY of curr because curr will
            // change as backtracking continues.
            res.add(new ArrayList<>(curr));
            return;
        }

        // HOW MANY MORE NUMBERS DO WE NEED?
        // k = total numbers required.
        // curr.size() = numbers already selected.
        int needed = k - curr.size();

        // OPTIMIZATION / PRUNING:
        // We cannot choose a number that leaves too few
        // numbers to complete the combination.
        //
        // Formula:
        // largest possible i = n - needed + 1
        //
        // Why?
        // After choosing i, the available numbers are:
        // i, i+1, i+2, ..., n
        //
        // Their count is n - i + 1.
        // We need at least 'needed' numbers:
        //
        // n - i + 1 >= needed
        //
        // Rearranging:
        // i <= n - needed + 1
        //
        // Since needed = k - curr.size():
        // i <= n - (k - curr.size()) + 1

        for (int i = start;
             i <= n - (k - curr.size()) + 1;
             i++) {

            // CHOOSE:
            // Add the current number to our combination.
            curr.add(i);

            // EXPLORE:
            // Continue with i + 1 because each number
            // can be used only once and order doesn't matter.
            backtrack(n, i + 1, k, curr);

            // UNDO / BACKTRACK:
            // Remove the last number so we can try
            // another possible choice.
            curr.remove(curr.size() - 1);
        }
    }
}
