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
        ArrayList<String> list = new ArrayList<>();
        if(s.isEmpty()){
            return "/";
        }
        while(!s.isEmpty()){
            list.add(s.pop());
            list.add("/");
        }
        StringBuilder sb = new StringBuilder();
        for(int i = list.size() - 1; i >= 0; i--){
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}