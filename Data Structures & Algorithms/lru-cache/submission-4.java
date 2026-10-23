// Each Node represents one entry in our LRU Cache.
//
// We need:
// key   -> so that we can remove a specific key from HashMap
// value -> the actual cached value
// prev/next -> to connect nodes in a Doubly Linked List
public class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;

        // Initially, this node is not connected to anything.
        this.prev = null;
        this.next = null;
    }
}


class LRUCache {

    // HashMap gives us O(1) access to a node using its key.
    //
    // Example:
    // map.get(5) -> directly gives us the Node having key = 5
    HashMap<Integer, Node> map;

    // Maximum number of elements the cache can hold.
    int size;

    // Dummy/sentinel nodes.
    //
    // left  = beginning of the list
    // right = end of the list
    //
    // We don't store actual cache data in these nodes.
    Node right;
    Node left;


    public LRUCache(int capacity) {

        // Store the maximum capacity.
        this.size = capacity;

        // Create an empty HashMap.
        this.map = new HashMap<>();

        // Create dummy nodes.
        //
        // The actual cache nodes will always exist between
        // left and right.
        this.left = new Node(0, 0);
        this.right = new Node(0, 0);


        // Initially the list looks like:
        //
        // left <-> right
        //
        // There are no actual cache nodes yet.
        this.left.next = this.right;
        this.right.prev = this.left;
    }


    // Removes a node from the Doubly Linked List.
    //
    // IMPORTANT:
    // We are only removing the node from the linked list.
    // We are NOT removing it from the HashMap here.
    public void remove(Node node) {

        // Get the nodes immediately before and after our node.
        Node prev = node.prev;
        Node next = node.next;


        // Connect prev directly to next.
        //
        // Before:
        //
        // prev <-> node <-> next
        //
        // After:
        //
        // prev <-> next
        prev.next = next;
        next.prev = prev;
    }


    // Inserts a node immediately before the right dummy node.
    //
    // This means the node becomes the MOST RECENTLY USED node.
    public void insert(Node node) {

        // right.prev is currently the last actual node.
        //
        // Example:
        //
        // left <-> A <-> B <-> right
        //
        // right.prev = B
        Node prev = this.right.prev;


        // Connect previous last node to our new node.
        prev.next = node;

        // Connect our new node back to previous node.
        node.prev = prev;

        // Our new node comes immediately before right.
        node.next = this.right;

        // right now points back to our new node.
        this.right.prev = node;


        // Result:
        //
        // left <-> A <-> B <-> node <-> right
        //
        // node is now the MOST RECENTLY USED.
    }


    
    public int get(int key) {

        // Check whether the key exists in our cache.
        if (map.containsKey(key)) {

            // Get the actual Node in O(1).
            Node node = map.get(key);


            // Since we accessed this node,
            // it is now the MOST RECENTLY USED node.
            //
            // So first remove it from its current position.
            remove(node);


            // Then insert it at the end (before right).
            //
            // This moves it to the MOST RECENTLY USED position.
            insert(node);


            // Return the cached value.
            return node.value;
        }


        // Key does not exist in cache.
        return -1;
    }


    
    public void put(int key, int value) {

        // CASE 1:
        // The key already exists.
        if (map.containsKey(key)) {

            // Get the existing node.
            Node node = map.get(key);

            // Remove the old node from the linked list.
            //
            // We will create a new node with the updated value below.
            remove(node);
        }


        // Create a new Node with the key and value.
        Node newNode = new Node(key, value);


        // Put the new node into the HashMap.
        //
        // If the key already existed, this replaces the
        // old Node reference with the new Node.
        map.put(key, newNode);


        // This new/updated node has just been used,
        // so it becomes the MOST RECENTLY USED node.
        insert(newNode);


        // If the cache has exceeded its capacity,
        // we need to remove the LEAST RECENTLY USED node.
        if (map.size() > size) {

            // left.next is the first actual node.
            //
            // Remember:
            //
            // left <-> LRU <-> ... <-> MRU <-> right
            //
            // Therefore, left.next = LRU node.
            Node lru = this.left.next;


            // Remove the LRU node from the linked list.
            remove(lru);


            // Remove the LRU key from the HashMap as well.
            //
            // IMPORTANT:
            // remove(lru) only changes the linked list.
            // We must separately remove it from the map.
            map.remove(lru.key);
        }
    }
}