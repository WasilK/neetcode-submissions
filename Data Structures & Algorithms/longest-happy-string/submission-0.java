class Solution {
    public String longestDiverseString(int a, int b, int c) {

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((x, y) -> y[1] - x[1]);

        if (a > 0) pq.offer(new int[]{'a', a});
        if (b > 0) pq.offer(new int[]{'b', b});
        if (c > 0) pq.offer(new int[]{'c', c});

        StringBuilder res = new StringBuilder();

        while (!pq.isEmpty()) {

            int[] first = pq.poll();

            // Can't use first because it would create "aaa"
            if (res.length() >= 2 &&
                res.charAt(res.length() - 1) == first[0] &&
                res.charAt(res.length() - 2) == first[0]) {

                if (pq.isEmpty()) {
                    break;
                }

                int[] second = pq.poll();

                res.append((char) second[0]);
                second[1]--;

                if (second[1] > 0) {
                    pq.offer(second);
                }

                pq.offer(first);

            } else {

                // Add first character
                res.append((char) first[0]);
                first[1]--;

                // Add it a second time if allowed
                if (first[1] > 0 &&
                    !(res.length() >= 2 &&
                      res.charAt(res.length() - 1) == first[0] &&
                      res.charAt(res.length() - 2) == first[0])) {

                    res.append((char) first[0]);
                    first[1]--;
                }

                if (first[1] > 0) {
                    pq.offer(first);
                }
            }
        }

        return res.toString();
    }
}