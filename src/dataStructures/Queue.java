package dataStructures;

/**
 * Generic Queue implementation using LinkedList
 * Used for managing attendance records in FIFO order
 */
public class Queue<T> {
    private LinkedList<T> list;

    public Queue() {
        this.list = new LinkedList<>();
    }

    public void enqueue(T item) {
        list.add(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list.remove(0);
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(T item) {
        return list.contains(item);
    }
}

