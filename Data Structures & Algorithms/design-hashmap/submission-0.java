class MyHashMap {

    static class Node {
        int key, val;
        Node next;

        public Node(int key, int val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public Node(){
            this(-1, -1, null);
        }
    }

    public Node set[];

    public int hash(int key){
        return key % set.length;
    }

    public MyHashMap() {
        set = new Node[1000];
        for(int i = 0; i < set.length; i++){
            set[i] = new Node();
        }
    }
    
    public void put(int key, int value) {
        Node curr = set[hash(key)];
        while(curr.next != null){
            if(curr.next.key == key){
                curr.next.val = value;
                return;
            }
            curr = curr.next;
        }
        curr.next = new Node(key, value, null);
    }
    
    public int get(int key) {
        Node curr = set[hash(key)];
        while(curr.next != null){
            if(curr.next.key == key){
                return curr.next.val;
            }
            curr = curr.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        Node curr = set[hash(key)];
        while(curr.next != null){
            if(curr.next.key == key){
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */