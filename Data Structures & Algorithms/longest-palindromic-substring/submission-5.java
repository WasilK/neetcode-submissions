class Solution {
    public String longestPalindrome(String s) {
        int st = 0;
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++){
            //odd
            int len1 = expand(s, i, i);
            //even
            int len2 = expand(s, i, i + 1);

            int len = Math.max(len1, len2);

            if(len > maxLen){
                maxLen = len;
                st = i - (len - 1) / 2;
            }
        }
        return s.substring(st, st + maxLen);
    }
    int expand(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }
}
