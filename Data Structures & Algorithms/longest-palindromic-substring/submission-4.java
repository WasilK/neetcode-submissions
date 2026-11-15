class Solution {
    public String longestPalindrome(String s) {
        String res = s.substring(0, 1);
        for(int i = 0; i < s.length(); i++){
            for(int j = i + 1; j < s.length(); j++){
                if(isPalindrome(s, i, j)){
                    if(j - i + 1 > res.length()){
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
    public boolean isPalindrome(String s, int i, int j){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
