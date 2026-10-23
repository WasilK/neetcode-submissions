class LRUCache {
    public class Node{
        int val, key;
        Node prev, next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int size;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.size = capacity;

        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        this.head.next = tail;
        this.tail.prev = head;
    }
    
    public void insert(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insert(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
            insert(node);
            return;
        }

        Node newNode = new Node(key, value);
        insert(newNode);
        map.put(key, newNode);

        if(map.size() > size){
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }
}
