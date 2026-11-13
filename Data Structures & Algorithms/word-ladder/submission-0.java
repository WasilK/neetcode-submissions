class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for(String words : wordList){
            set.add(words);
        }
        if(!set.contains(endWord)){
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        int steps = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String word = q.remove();
                if(word.equals(endWord)){
                    return steps;
                }
                for(int j = 0; j < word.length(); j++){
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        String str = word.substring(0, j) + ch + word.substring(j + 1);
                        if(set.contains(str)){
                            q.add(str);
                            set.remove(str);
                        }
                    }
                }
            }
            steps++;
        }
        return 0;
    }
}
