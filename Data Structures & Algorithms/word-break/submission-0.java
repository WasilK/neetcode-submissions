class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int maxLen = 0;
        for(String word : wordDict){
            set.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        int st = 0;
        Boolean dp[] = new Boolean[s.length()];
        return mem(s, st, set, maxLen, dp);
    }
    boolean mem(String s, int st, Set<String> set, int maxLen, Boolean dp[]){
        if(st == s.length()){
            return true;
        }
        if(dp[st] != null){
            return dp[st];
        }
        for(int en = st + 1; en <= Math.min(st + maxLen, s.length()); en++){
            if(set.contains(s.substring(st, en)) && mem(s, en, set, maxLen, dp)){
                return dp[st] = true;
            }
        }
        return dp[st] = false;
    }
}
