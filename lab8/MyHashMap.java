
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Collections;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private ArrayList<Node> map;
    private int size;
    private double loadFactor;
    private HashSet<K> keys;
    private int initialSize;

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor <= 0.0)
            throw new IllegalArgumentException ();
        map = new ArrayList<Node>(initialSize);
        map.addAll(Collections.nCopies(initialSize, null));
        size = 0;
        this.loadFactor = loadFactor;
        keys = new HashSet<K>();
        this.initialSize = initialSize;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        map = new ArrayList<Node>(initialSize);
        map.addAll(Collections.nCopies(initialSize, null));
        size = 0;
        keys = new HashSet<K>();
    }

    private void resize() {
        MyHashMap<K, V> newMap
                = new MyHashMap(map.size()*2, loadFactor);

        for (Object key : keys) {
            newMap.put((K) key, this.get((K) key));
        }

        size = newMap.size;
        map = newMap.map;
        loadFactor = newMap.loadFactor;
        keys = newMap.keys;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (0x7fffffff & key.hashCode()) % map.size();
    }

    private Node find (K key, Node bin) {
        for (Node e = bin; e != null; e = e.next)
            if (key == null && e.key == null || key.equals (e.key))
                return e;
        return null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (keys.contains(key)) {
            return true;
        }
        return false;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node e = find (key, map.get(hash(key)));
        return (e == null) ? null : e.val;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int h = hash(key);
        Node e = find(key, map.get(h));
        if (e == null) {
            map.set(h, new Node(key, value, map.get(h)));
            size += 1;
            keys.add(key);
            if (size > map.size() * loadFactor)
                resize();
        } else
            e.val = value;
    }


    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keys;
    }





    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
