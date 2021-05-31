import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;             // root of BST
    private int size = 0;

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root,key);
    }
    public boolean containsKey(Node p, K key) {
        if (p == null)
            return false;
        if (key.equals(p.key))
            return true;
        else if (key.compareTo(p.key) < 0)
            return containsKey(p.left, key);
        else
            return containsKey(p.right, key);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root,key);
    }
    public V get(Node p, K key) {
        if (p == null)
            return null;
        if (key.equals(p.key))
            return p.val;
        else if (key.compareTo(p.key) < 0)
            return get(p.left, key);
        else
            return get(p.right, key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }
    public Node put(Node p, K key, V value) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(p.key) < 0)
            p.left = put(p.left, key, value);
        else if (key.compareTo(p.key) > 0)
            p.right = put(p.right, key, value);
        return p;
    }

    /** prints out the BSTMap in order of increasing Key. */
    public void printInOrder() {
        printInOrder(root);
    }
    public void printInOrder(Node p) {
        if (p == null)
            return;
        printInOrder(p.left);
        System.out.println(p.key.toString() + "  " + p.val.toString());
        printInOrder(p.right);
    }









    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /** Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

}
