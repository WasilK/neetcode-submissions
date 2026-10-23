class Solution {
    public String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";

        HashMap<Character, Integer> tCount = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for(char ch : t.toCharArray()){
            tCount.put(ch, tCount.getOrDefault(ch, 0) + 1);
        }

        int res[] = {-1, -1};
        int len = Integer.MAX_VALUE;
        int l = 0;
        int have = 0, need = tCount.size();

        for(int r = 0; r < s.length(); r++){
            char ch = s.charAt(r);
            window.put(ch, window.getOrDefault(ch, 0) + 1);

            if(tCount.containsKey(ch) && window.get(ch).equals(tCount.get(ch))) have++;

            while(have == need){
                if((r - l + 1) < len){
                    len = r - l + 1;
                    res[0] = l;
                    res[1] = r;
                }
                window.put(s.charAt(l), window.get(s.charAt(l)) - 1);
                if(tCount.containsKey(s.charAt(l)) && window.get(s.charAt(l)) < tCount.get(s.charAt(l))) have--;
                l++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
    }
}
