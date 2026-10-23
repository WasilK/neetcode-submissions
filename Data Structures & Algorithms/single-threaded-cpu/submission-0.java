class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int arr[][] = new int[n][3];

        for(int i = 0; i < n; i++){
            arr[i][0] = tasks[i][0];
            arr[i][1] = tasks[i][1];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]
        );

        int res[] = new int[n];
        int idx = 0;
        int i = 0;
        int time = 0;

        while(idx < n){

            if(pq.isEmpty() && time < arr[i][0]){
                time = arr[i][0];
            }

            while(i < n && arr[i][0] <= time){
                pq.offer(arr[i]);
                i++;
            }

            int task[] = pq.poll();
            res[idx++] = task[2];

            time += task[1];
        }

        return res;
    }
}