class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < asteroids.length; i++){
            int curr = asteroids[i];
            while(!s.isEmpty() && curr < 0 && s.peek() > 0){
                int diff = curr + s.peek();
                if(diff > 0){
                    curr = 0;
                }else if(diff < 0){
                    s.pop();
                }else{
                    s.pop();
                    curr = 0;
                }
            }
            if(curr != 0){
                s.push(curr);
            }
        }
       int res[] = new int[s.size()];
       for(int i = s.size() - 1; i >= 0; i--){
         res[i] = s.pop();
       }
       return res; 
    }
}