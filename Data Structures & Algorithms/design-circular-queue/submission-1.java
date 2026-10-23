class MyCircularQueue {
    LinkedList<Integer> list;
    int size;
    public MyCircularQueue(int k) {
        list = new LinkedList<>();
        size = k;
    }
    
    public boolean enQueue(int value) {
        if(list.size() == size){
            return false;
        }else{
            list.add(value);
            return true;
        }
    }
    
    public boolean deQueue() {
        if(!list.isEmpty()){
            list.remove(0);
            return true;
        }else{
            return false;
        }
    }
    
    public int Front() {
        if(list.isEmpty()) return -1;
        return list.get(0);
    }
    
    public int Rear() {
        if(list.isEmpty()) return -1;
        return list.get(list.size() - 1);
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public boolean isFull() {
        return list.size() == size;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */