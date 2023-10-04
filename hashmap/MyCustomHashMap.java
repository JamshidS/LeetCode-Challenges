public class MyCustomHashMap {
    private static final int DEFAULT_CAPACITY = 16;
    private final int[] keys;
    private final int[] values;
    private final int[] next;
    private final int[] buckets;
    private final int capacity;
    private int size;

    public MyCustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyCustomHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.keys = new int[capacity];
        this.values = new int[capacity];
        this.next = new int[capacity];
        this.buckets = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = -1;
        }
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        int current = buckets[index];

        while (current != -1) {
            if (keys[current] == key) {
                values[current] = value;
                return;
            }
            current = next[current];
        }

        keys[size] = key;
        values[size] = value;
        next[size] = buckets[index];
        buckets[index] = size;
        size++;
    }

    public int get(int key) {
        int index = getIndex(key);
        int current = buckets[index];

        while (current != -1) {
            if (keys[current] == key) {
                return values[current];
            }
            current = next[current];
        }

        return -1; // Key not found
    }

    public void remove(int key) {
        int index = getIndex(key);
        int current = buckets[index];
        int prev = -1;

        while (current != -1) {
            if (keys[current] == key) {
                if (prev == -1) {
                    buckets[index] = next[current];
                } else {
                    next[prev] = next[current];
                }
                return;
            }
            prev = current;
            current = next[current];
        }
    }

    public int size() {
        return size;
    }

    private int getIndex(int key) {
        return Math.abs(key) % capacity;
    }

}
