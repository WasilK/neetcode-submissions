class Solution {
    public String reorganizeString(String s) {
        int freq[] = new int[26];
        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int i = 0; i < 26; i++){
            if(freq[i] > 0){
                pq.offer(new int[]{i, freq[i]});
            }
        }
        StringBuilder res = new StringBuilder();
        while(pq.size() >= 2){
            int first[] = pq.poll();
            int second[] = pq.poll();
            res.append((char) (first[0] + 'a'));
            res.append((char) (second[0] + 'a'));
            first[1]--;
            second[1]--;
            if(first[1] > 0) pq.offer(first);
            if(second[1] > 0) pq.offer(second);
        }
        if(!pq.isEmpty()){
            int last[] = pq.poll();
            if(last[1] > 1){
                return "";
            }
            res.append((char) (last[0] + 'a'));
        }
        return res.toString();
    }
}