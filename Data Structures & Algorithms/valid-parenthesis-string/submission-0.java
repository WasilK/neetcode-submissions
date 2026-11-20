class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                left.push(i);
            }else if(ch == ')'){
                if(left.isEmpty() && star.isEmpty()){
                    return false;
                }
                if(!left.isEmpty()){
                    left.pop();
                }else{
                    star.pop();
                }
            }else{
                star.push(i);
            }
        }

        while(!left.isEmpty() && !star.isEmpty()){
            if(left.pop() > star.pop()){
                return false;
            }
        }
        return left.isEmpty();
    }
}
