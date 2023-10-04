public class MyHashMap {

    private static final int DEFAULT_CAPACITY = 16;
    private Node<Integer>[] buckets;
    private final int capacity;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buckets = new Node[capacity];
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        Node<Integer> newNode = new Node<>(key, value);
        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node<Integer> current = buckets[index];
            while (current.next != null) {
                if (current.key==key) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key==key) {
                current.value = value;
            } else {
                current.next = newNode;
            }
        }
        size++;
    }

    public void remove(int key){
        int index = getIndex(key);
        Node<Integer> current = buckets[index];
        Node<Integer> prev = null;

        while (current!=null){
            if(current.key==key){
                if(prev == null){
                    buckets[index] = current.next;
                }else{
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public int get(int key) {
        int index = getIndex(key);
        Node<Integer> current = buckets[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return -1;
    }

    private int getIndex(Integer key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private static class Node<Integer> {
        private final int key;
        private int value;
        private Node<java.lang.Integer> next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
