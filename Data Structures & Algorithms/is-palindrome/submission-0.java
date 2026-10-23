class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                sb.append(Character.toLowerCase(ch));
            }
        }
        String res = sb.toString();
        for(int i = 0; i < res.length(); i++){
            if(res.charAt(i) != res.charAt(res.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
}
