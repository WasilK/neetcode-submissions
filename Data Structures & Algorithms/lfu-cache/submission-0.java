class LFUCache {
    class Node{
        int key, val, freq;
        Node prev, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }
    class DLL{
        Node head, tail;
        int size;

        public DLL(){
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);

            this.head.next = tail;
            this.tail.prev = head;

            this.size = 0;
        }

        public void insert(Node node){
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        public void remove(Node node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;
        }

        public Node removeLast(){
            if(size > 0){
                Node last = tail.prev;
                remove(last);
                return last;
            }
            return null;
        }
    }
    int capacity;
    int size;
    int minFreq;

    HashMap<Integer, Node> nodeMap;
    HashMap<Integer, DLL> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 1;

        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if(!nodeMap.containsKey(key)){
            return -1;
        }
        Node node = nodeMap.get(key);
        updateFreq(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }
        if(nodeMap.containsKey(key)){
            Node node = nodeMap.get(key);
            node.val = value;
            updateFreq(node);
            return;
        }
        if(size == capacity){
            DLL list = freqMap.get(minFreq);
            Node nodeToRemove = list.removeLast();
            nodeMap.remove(nodeToRemove.key);
            size--;
        }
        Node newNode = new Node(key, value);
        nodeMap.put(key, newNode);

        DLL list = freqMap.getOrDefault(1, new DLL());
        list.insert(newNode);
        freqMap.put(1, list);

        minFreq = 1;
        size++;
    }
    public void updateFreq(Node node){
        int freq = node.freq;
        DLL list = freqMap.get(freq);

        list.remove(node);

        if(freq == minFreq && list.size == 0){
            minFreq++;
        }
        node.freq++;

        DLL newList = freqMap.getOrDefault(node.freq,new DLL());
        newList.insert(node);
        freqMap.put(node.freq,newList);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */