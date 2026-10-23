class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int closest[][] = new int[n][3];
        for(int i = 0; i < n; i++){
            closest[i][0] = i;
            closest[i][1] = arr[i];
            closest[i][2] = Math.abs(arr[i] - x);
        }
        Arrays.sort(closest, Comparator.comparingDouble(o -> o[2]));
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < k; i++){
            res.add(closest[i][1]);
        }
        Collections.sort(res);
        return res;
    }
}