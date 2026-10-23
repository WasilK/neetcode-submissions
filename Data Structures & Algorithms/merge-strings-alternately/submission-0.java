class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int fp = 0, sp = 0;
        while(fp < word1.length() || sp < word2.length()){
            if(fp < word1.length()){
                sb.append(word1.charAt(fp++));
            }
            if(sp < word2.length()){
                sb.append(word2.charAt(sp++));
            }
        }
    
        return sb.toString();
    }
}