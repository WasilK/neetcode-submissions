class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();

        for(int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);

            if(count.size() > 2){
                HashMap<Integer, Integer> newCount = new HashMap<>();
                for(int key : count.keySet()){
                    newCount.put(key, count.get(key) - 1);
                }
                count = newCount;
            }
        }

        for(int key : count.keySet()){
            int freq = 0;
            for(int num : nums){
                if(key == num) freq++;
            }
            if(freq > nums.length / 3) res.add(key);
        }
        return res;

    }
}