class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int n = nums.length;
        ArrayList<Integer> freq[] = new ArrayList[n + 1];

        for(int i = 1; i < freq.length; i++){
            freq[i] = new ArrayList<>();
        }

        for(int key : map.keySet()){
            freq[map.get(key)].add(key);
        }

        int res[] = new int[k];
        int idx = 0;

        for(int i = freq.length - 1; i >= 0 && idx < k; i--){
            for(int num : freq[i]){
                res[idx++] = num;
                if(idx == k) return res;
            }
        }
        return res;
    }
}
