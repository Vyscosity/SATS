package dataStructures;

/**
 * Hash Table implementation for fast student lookups
 * Uses chaining for collision resolution
 */
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;
    private int capacity;

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }

        int index = hash(key);
        Entry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<>(key, value);
            size++;
            return;
        }

        Entry<K, V> prev = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            prev = entry;
            entry = entry.next;
        }

        prev.next = new Entry<>(key, value);
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];

        if (entry == null) {
            return null;
        }

        if (entry.key.equals(key)) {
            table[index] = entry.next;
            size--;
            return entry.value;
        }

        Entry<K, V> prev = entry;
        entry = entry.next;

        while (entry != null) {
            if (entry.key.equals(key)) {
                prev.next = entry.next;
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int oldCapacity = capacity;
        capacity *= 2;
        Entry<K, V>[] oldTable = table;
        table = new Entry[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            Entry<K, V> entry = oldTable[i];
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        size = 0;
    }

    public java.util.List<K> keys() {
        java.util.List<K> keyList = new java.util.ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                keyList.add(entry.key);
                entry = entry.next;
            }
        }
        return keyList;
    }

    public java.util.List<V> values() {
        java.util.List<V> valueList = new java.util.ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                valueList.add(entry.value);
                entry = entry.next;
            }
        }
        return valueList;
    }
}

