class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i < j){
            
            while(i < j && !isAlphaNumeric(s.charAt(i))) i++;
            while(j > i && !isAlphaNumeric(s.charAt(j))) j--;

            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++;
            j--;
        }

        return true;
    }

    public boolean isAlphaNumeric(char ch){
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }
}
