class Node{
    Node prev, next;
    int key, val, freq;

    public Node(int key, int val){
        this.key = key;
        this.val = val;
        this.freq = 1;
        this.prev = null;
        this.next = null;
    }
}

class DLL{
    Node head, tail;

    public DLL(){
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
        
    }

    public void insert(Node node){
        Node next = this.head.next;
        this.head.next = node;
        node.next = next;
        node.prev = head;
        next.prev = node;
    }

    public Node removeLast(){
        if(head.next == tail) return null;
        Node node = tail.prev;
        remove(node);
        return node;
    }

    public boolean isEmpty(){
        return head.next == tail;
    }
}

class LFUCache {

    int capacity;
    int minFreq;

    HashMap<Integer, DLL> freqList;
    HashMap<Integer, Node> keyNode;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.freqList = new HashMap<>();
        this.keyNode = new HashMap<>();
    }
    
    public int get(int key) {
        if(!keyNode.containsKey(key)) return -1;

        Node node = keyNode.get(key);
        increaseFreq(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if(keyNode.containsKey(key)){
            Node node = keyNode.get(key);
            node.val = value;
            increaseFreq(node);
            return;
        }
        if(keyNode.size() == capacity){
            DLL list = freqList.get(minFreq);
            Node nodeToRemove = list.removeLast();
            keyNode.remove(nodeToRemove.key);
        }

        Node node = new Node(key, value);
        keyNode.put(key, node);
        DLL list = freqList.getOrDefault(1, new DLL());
        list.insert(node);
        freqList.put(1, list);
        minFreq = 1;
    }

    public void increaseFreq(Node node){
        int oldFreq = node.freq;
        DLL oldList = freqList.get(oldFreq);

        oldList.remove(node);

        if(oldFreq == minFreq && oldList.isEmpty()){
            minFreq++;
        }

        node.freq++;

        DLL newList = freqList.getOrDefault(node.freq,  new DLL());

        newList.insert(node);
        freqList.put(node.freq, newList);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */