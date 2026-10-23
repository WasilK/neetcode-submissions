class MyHashMap {

    // 🔹 Node class represents each element in the linked list (bucket)
    static class Node {
        int key, val;   // key-value pair
        Node next;      // pointer to next node (for collision handling)

        // 🔹 Parameterized constructor
        // Used to create actual data nodes
        public Node(int key, int val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;  // link to next node
        }

        // 🔹 Default constructor (Dummy / Sentinel node)
        // This avoids null checks and simplifies logic
        public Node(){
            this(-1, -1, null);
        }
    }

    // 🔹 Array of linked lists (buckets)
    // Each index stores a dummy node
    public Node set[];

    // 🔹 Hash function
    // Maps a key to an index in array
    public int hash(int key){
        return key % set.length;  // simple modulo hashing
    }

    // 🔹 Constructor
    public MyHashMap() {
        set = new Node[1000];  // fixed size bucket array

        // Initialize each bucket with a dummy node
        for(int i = 0; i < set.length; i++){
            set[i] = new Node();
        }
    }
    
    // 🔹 PUT → Insert or Update
    public void put(int key, int value) {

        // Start from dummy node of that bucket
        Node curr = set[hash(key)];

        // Traverse linked list
        while(curr.next != null){

            // 🔥 If key already exists → update value
            if(curr.next.key == key){
                curr.next.val = value;
                return;
            }

            curr = curr.next;
        }

        // 🔥 Key not found → insert new node at end
        curr.next = new Node(key, value, null);
    }
    
    // 🔹 GET → Retrieve value
    public int get(int key) {

        Node curr = set[hash(key)];

        // Traverse list
        while(curr.next != null){

            // If key found → return value
            if(curr.next.key == key){
                return curr.next.val;
            }

            curr = curr.next;
        }

        // 🔥 Key not found
        return -1;
    }
    
    // 🔹 REMOVE → Delete key
    public void remove(int key) {

        Node curr = set[hash(key)];

        // Traverse list
        while(curr.next != null){

            // 🔥 If next node has the key → skip it
            if(curr.next.key == key){
                curr.next = curr.next.next;  // delete node
                return;
            }

            curr = curr.next;
        }
    }
}