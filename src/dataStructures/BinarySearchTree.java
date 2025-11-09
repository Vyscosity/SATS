package dataStructures;

/**
 * Binary Search Tree implementation for efficient searching
 * Used for searching students by ID or name
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public void insert(T data) {
        root = insertRecursive(root, data);
        size++;
    }

    private Node<T> insertRecursive(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insertRecursive(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRecursive(node.right, data);
        }
        return node;
    }

    public boolean search(T data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node<T> node, T data) {
        if (node == null) {
            return false;
        }
        if (data.compareTo(node.data) == 0) {
            return true;
        }
        if (data.compareTo(node.data) < 0) {
            return searchRecursive(node.left, data);
        }
        return searchRecursive(node.right, data);
    }

    public T find(T data) {
        Node<T> node = findRecursive(root, data);
        return node != null ? node.data : null;
    }

    private Node<T> findRecursive(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.data) == 0) {
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            return findRecursive(node.left, data);
        }
        return findRecursive(node.right, data);
    }

    public void delete(T data) {
        root = deleteRecursive(root, data);
        size--;
    }

    private Node<T> deleteRecursive(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.data) < 0) {
            node.left = deleteRecursive(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = deleteRecursive(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.data = minValue(node.right);
            node.right = deleteRecursive(node.right, node.data);
        }
        return node;
    }

    private T minValue(Node<T> node) {
        T min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    public void inOrderTraversal(java.util.List<T> result) {
        inOrderRecursive(root, result);
    }

    private void inOrderRecursive(Node<T> node, java.util.List<T> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.data);
            inOrderRecursive(node.right, result);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }
}

