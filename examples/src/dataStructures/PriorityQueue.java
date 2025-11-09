package dataStructures;

/**
 * Priority Queue (Min Heap) implementation
 * Used for alerts on late or absent students
 */
public class PriorityQueue<T extends Comparable<T>> {
    private java.util.ArrayList<T> heap;
    private boolean isMinHeap;

    public PriorityQueue() {
        this(true);
    }

    public PriorityQueue(boolean isMinHeap) {
        this.heap = new java.util.ArrayList<>();
        this.isMinHeap = isMinHeap;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private boolean compare(int i, int j) {
        if (isMinHeap) {
            return heap.get(i).compareTo(heap.get(j)) < 0;
        } else {
            return heap.get(i).compareTo(heap.get(j)) > 0;
        }
    }

    public void enqueue(T item) {
        heap.add(item);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && compare(index, parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Priority queue is empty");
        }
        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return root;
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size() && compare(left, smallest)) {
            smallest = left;
        }
        if (right < heap.size() && compare(right, smallest)) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Priority queue is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }
}

