class Solution {
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indeg = new HashMap<>();

        for(String word : words){
            for(char ch : word.toCharArray()){
                graph.putIfAbsent(ch, new HashSet<>());
                indeg.putIfAbsent(ch, 0);
            }
        }

        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i + 1];

            int j = 0;
            int len = Math.min(word1.length(), word2.length());
            while(j < len && word1.charAt(j) == word2.charAt(j)){
                j++;
            }
            if(j == len){
                if(word1.length() > word2.length()){
                    return "";
                }
                continue;
            }

            char u = word1.charAt(j);
            char v = word2.charAt(j);

            if(graph.get(u).add(v)){
                indeg.put(v, indeg.getOrDefault(v, 0) + 1);
            }
        }

        Queue<Character> q = new LinkedList<>();

        for(char ch : indeg.keySet()){
            if(indeg.get(ch) == 0){
                q.add(ch);
            }
        }

        StringBuilder ans = new StringBuilder();

        while(!q.isEmpty()){
            char curr = q.remove();
            ans.append(curr);

            for(char next : graph.get(curr)){
                indeg.put(next, indeg.get(next) - 1);
                if(indeg.get(next) == 0){
                    q.add(next);
                }
            }
        }
        if(ans.length() != indeg.size()){
            return "";
        }
        return ans.toString();
    }
}
