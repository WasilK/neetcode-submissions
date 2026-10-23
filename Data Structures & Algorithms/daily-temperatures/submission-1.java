class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> s = new Stack<>();
        int n = temperatures.length;
        int res[] = new int[n];
        res[n - 1] = 0;
        s.push(n - 1);

        for(int i = n - 2; i >= 0; i--){
            while(!s.isEmpty() && temperatures[i] >= temperatures[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                res[i] = 0;
            }else{
                res[i] = s.peek() - i;
            }
            s.push(i);
        }
        return res;
    }
}
