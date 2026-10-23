class Solution {
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<>();
        String arr[] = path.split("/");
        for(int i = 0; i < arr.length; i++){
            String str = arr[i];
            if(!str.equals("") && !str.equals("..") && !str.equals(".")){
                s.push(str);
            }
            if(str.equals("..") && !s.isEmpty()){
                s.pop();
            }
        }
    
        if(s.isEmpty()){
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for(String str : s){
            sb.append("/");
            sb.append(str);
        }
        return sb.toString();
    }
}