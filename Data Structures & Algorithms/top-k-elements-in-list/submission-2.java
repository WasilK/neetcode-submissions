class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<int []> arr = new ArrayList<>();
        for(int key : map.keySet()){
            arr.add(new int[] {map.get(key), key});
        }
        arr.sort((a, b) -> b[0] - a[0]);
        int res[] = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = arr.get(i)[1];
        }
        return res;
    }
}
