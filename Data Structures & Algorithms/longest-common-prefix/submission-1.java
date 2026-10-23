class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

        for(String str : strs){
            int j = 0;
            while(j < Math.min(str.length(), prefix.length())){
                if(str.charAt(j) != prefix.charAt(j)) break;
                j++;
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }
}