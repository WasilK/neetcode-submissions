class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> orderMap = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            char ch = order.charAt(i);
            orderMap.put(ch, i);
        }
        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i + 1];
            
            int p = 0;
            while(p < word1.length() && p < word2.length()){
                char ch1 = word1.charAt(p);
                char ch2 = word2.charAt(p);

                if(ch1 != ch2){
                    if(orderMap.get(ch1) > orderMap.get(ch2)){
                        return false;
                    }
                    break;
                }
                p++;
            }
            if(p == word2.length() && word1.length() > word2.length()){
                return false;
            }
        }
        return true;
    }
}