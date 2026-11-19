class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0){
            return false;
        }
        Arrays.sort(hand);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int h : hand){
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        for(int h : hand){
            if(map.get(h) == 0){
                continue;
            }
            for(int i = 0; i < groupSize; i++){
                int curr = h + i;
                if(map.getOrDefault(curr, 0) == 0){
                    return false;
                }
                map.put(curr, map.get(curr) - 1);
            }
        }
        return true;
    }
}
