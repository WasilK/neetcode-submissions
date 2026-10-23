
/*
 * Each Node represents one key-value pair in the cache.
 *
 * freq = number of times this key has been accessed.
 *
 * prev and next are used because every frequency has its own
 * doubly linked list.
 */
class Node {
    Node prev, next;
    int key, val, freq;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;

        // Every newly inserted node starts with frequency 1
        this.freq = 1;

        this.prev = null;
        this.next = null;
    }
}


/*
 * Doubly Linked List
 *
 * We maintain one DLL for every frequency.
 *
 * Example:
 *
 * freq = 2
 *
 * head <-> Node1 <-> Node5 <-> Node3 <-> tail
 *
 * The node closest to head = Most Recently Used
 * The node closest to tail = Least Recently Used
 *
 * Why do we need LRU?
 *
 * LFU says:
 * 1. Remove the node with the lowest frequency.
 * 2. If multiple nodes have the same frequency,
 *    remove the least recently used one.
 */
class DLL {

    Node head, tail;

    public DLL() {

        /*
         * Dummy head and tail nodes.
         *
         * They make insertion and deletion easier because
         * we don't have to handle special cases for the
         * first or last real node.
         */
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }


    /*
     * Remove a node from this DLL.
     *
     * Since we already have the Node object,
     * we can remove it in O(1).
     */
    public void remove(Node node) {

        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }


    /*
     * Insert node at the FRONT of the DLL.
     *
     * Front = Most Recently Used.
     *
     * Example:
     *
     * Before:
     * head <-> A <-> B <-> tail
     *
     * insert(C)
     *
     * After:
     * head <-> C <-> A <-> B <-> tail
     */
    public void insert(Node node) {

        Node next = this.head.next;

        this.head.next = node;

        node.next = next;
        node.prev = head;

        next.prev = node;
    }


    /*
     * Remove the last real node.
     *
     * The node closest to tail is the Least Recently Used
     * node among nodes having the same frequency.
     *
     * This is used during eviction.
     */
    public Node removeLast() {

        // No real nodes exist
        if (head.next == tail) {
            return null;
        }

        // Last real node = least recently used
        Node node = tail.prev;

        remove(node);

        return node;
    }


    /*
     * Check whether this frequency list contains no nodes.
     */
    public boolean isEmpty() {
        return head.next == tail;
    }
}


class LFUCache {

    int capacity;

    /*
     * minFreq tells us the lowest frequency currently present
     * in the cache.
     *
     * This allows us to find the eviction list in O(1).
     */
    int minFreq;


    /*
     * freqList:
     *
     * frequency -> DLL containing all nodes with that frequency
     *
     * Example:
     *
     * 1 -> DLL containing A, B
     * 2 -> DLL containing C
     * 3 -> DLL containing D, E
     */
    HashMap<Integer, DLL> freqList;


    /*
     * keyNode:
     *
     * key -> Node
     *
     * This allows us to find a key immediately in O(1).
     *
     * Example:
     *
     * 10 -> Node(10, 100, freq=2)
     */
    HashMap<Integer, Node> keyNode;


    public LFUCache(int capacity) {

        this.capacity = capacity;

        // Initially there are no nodes,
        // so minimum frequency is 0.
        this.minFreq = 0;

        freqList = new HashMap<>();
        keyNode = new HashMap<>();
    }


    /*
     * GET operation
     *
     * 1. Check whether key exists.
     * 2. Find its Node using keyNode.
     * 3. Accessing the key increases its frequency.
     * 4. Return its value.
     */
    public int get(int key) {

        // Key doesn't exist
        if (!keyNode.containsKey(key)) {
            return -1;
        }

        // Find the node in O(1)
        Node node = keyNode.get(key);

        /*
         * The node was accessed,
         * so its frequency must increase.
         */
        increaseFreq(node);

        return node.val;
    }


    /*
     * PUT operation
     */
    public void put(int key, int value) {

        /*
         * If capacity is 0, we cannot store anything.
         */
        if (capacity == 0) {
            return;
        }


        /*
         * CASE 1:
         * Key already exists.
         *
         * We don't create a new Node.
         *
         * We update the value and increase its frequency
         * because updating a key also counts as using it.
         */
        if (keyNode.containsKey(key)) {

            Node node = keyNode.get(key);

            node.val = value;

            increaseFreq(node);

            return;
        }


        /*
         * CASE 2:
         * Key doesn't exist and cache is full.
         *
         * We need to evict one node.
         *
         * minFreq tells us which frequency to look at.
         */
        if (keyNode.size() == capacity) {

            /*
             * Get the DLL containing nodes with the
             * minimum frequency.
             */
            DLL list = freqList.get(minFreq);


            /*
             * Among nodes with the same frequency,
             * remove the least recently used one.
             */
            Node nodeToRemove = list.removeLast();


            /*
             * Remove the evicted key from keyNode.
             *
             * Otherwise keyNode would still point to
             * a node that no longer exists in the cache.
             */
            keyNode.remove(nodeToRemove.key);
        }


        /*
         * CASE 3:
         * Create a completely new node.
         *
         * New nodes always start with frequency = 1.
         */
        Node node = new Node(key, value);


        /*
         * Store key -> node mapping.
         */
        keyNode.put(key, node);


        /*
         * Get the DLL for frequency 1.
         *
         * If frequency 1 doesn't exist yet,
         * create a new DLL.
         */
        DLL list = freqList.getOrDefault(1, new DLL());


        /*
         * Insert the new node at the front.
         *
         * Since it was just inserted,
         * it is the Most Recently Used node.
         */
        list.insert(node);


        /*
         * IMPORTANT:
         * Store the DLL back in freqList.
         */
        freqList.put(1, list);


        /*
         * Every new node has frequency 1,
         * so the minimum frequency becomes 1.
         */
        minFreq = 1;
    }


    /*
     * Increase the frequency of a node.
     *
     * This is the most important operation in LFU.
     *
     * Suppose:
     *
     * Node A has freq = 2
     *
     * We access A.
     *
     * It must move:
     *
     * frequency 2 DLL
     *        ↓
     * remove A
     *
     * frequency 3 DLL
     *        ↓
     * insert A
     */
    public void increaseFreq(Node node) {

        /*
         * Save the old frequency before changing it.
         */
        int oldFreq = node.freq;


        /*
         * Find the DLL containing this node.
         */
        DLL oldList = freqList.get(oldFreq);


        /*
         * Remove the node from its old frequency list.
         *
         * It cannot stay in both frequency lists.
         */
        oldList.remove(node);


        /*
         * What if this node was the ONLY node
         * having the minimum frequency?
         *
         * Example:
         *
         * minFreq = 2
         *
         * freq 2: A
         * freq 3: B
         *
         * If A's frequency increases:
         *
         * freq 2: empty
         * freq 3: A, B
         *
         * Therefore minimum frequency becomes 3.
         */
        if (oldFreq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }


        /*
         * Increase the node's frequency.
         */
        node.freq++;


        /*
         * Find the DLL for the new frequency.
         *
         * If it doesn't exist, create one.
         */
        DLL newList =
                freqList.getOrDefault(node.freq, new DLL());


        /*
         * Insert node at the FRONT.
         *
         * Why?
         *
         * Because this node was just accessed,
         * so it is now the Most Recently Used node
         * among nodes having this frequency.
         */
        newList.insert(node);


        /*
         * Store the updated DLL in freqList.
         */
        freqList.put(node.freq, newList);
    }
}