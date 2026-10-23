public class Node {
    int key;
    Node next;

    public Node(int key){
        this.key = key;
    }
}

class MyHashSet {
    public Node set[];
    public MyHashSet() {
        set = new Node[10000];
        for(int i = 0; i < set.length; i++){
            set[i] = new Node(0);
        }
    }
    
    public void add(int key) {
        Node curr = set[key % set.length];
        while(curr.next != null){
            if(curr.next.key == key) return;
            curr = curr.next;
        }
        curr.next = new Node(key);
    }
    
    public void remove(int key) {
        Node curr = set[key % set.length];
        while(curr.next != null){
            if(curr.next.key == key){
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }
    
    public boolean contains(int key) {
        Node curr = set[key % set.length];
        while(curr.next != null){
            if(curr.next.key == key){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */