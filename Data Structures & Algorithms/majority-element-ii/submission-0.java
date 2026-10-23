class Solution {
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
            if(count.size() > 2){
                HashMap<Integer, Integer> newCount = new HashMap<>();
                for(int key : count.keySet()){
                    if(count.get(key) > 1){
                        newCount.put(key, count.get(key) - 1);
                    }
                }
                count = newCount;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int key : count.keySet()){
            int freq = 0;
            for(int num : nums){
                if(num == key){
                    freq++;
                }
            }
            if(freq > nums.length / 3){
                    res.add(key);
                }
        }
        return res;
    }
}