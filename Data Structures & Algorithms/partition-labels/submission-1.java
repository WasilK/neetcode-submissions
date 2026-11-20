class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, i);
        }
        int first = 0;
        int last = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(map.get(ch) != i){
                last = Math.max(map.get(ch), last);
            }
            if(i == last){
                res.add(last - first + 1);
                last = i + 1;
                first = i + 1;
            }
        }
        return res;
    }
}
