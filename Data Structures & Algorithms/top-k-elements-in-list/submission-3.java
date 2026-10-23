class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for(int key : map.keySet()){
            pq.add(new int[] {map.get(key), key});
        }
        int res[] = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = pq.remove()[1];
        }
        return res;
    }
}
