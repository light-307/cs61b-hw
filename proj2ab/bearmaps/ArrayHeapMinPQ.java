package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

//import bearmaps.proj2ab.ExtrinsicMinPQ;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<T> minPQ;
    private int size;
    private HashMap<T,Double> maps;
    private HashMap<T,Integer> p_map;

    public ArrayHeapMinPQ() {
        minPQ = new ArrayList<T>();
        minPQ.add(0, null);
        size = 0;
        maps = new HashMap<T,Double>();
        p_map = new HashMap<T,Integer>();
    }

    /** Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }

        minPQ.add(size+1, item);
        maps.put(item, priority);
        p_map.put(item, size+1);
        swim(size+1);
        size = size + 1;
    }
    private void swim(int k) {
        if (parent(k) != 0 && maps.get(minPQ.get(parent(k))) > maps.get(minPQ.get(k))) {
            swap(k, parent(k));
            swim(parent(k));
        }
    }
    private int parent(int k) {
        return k / 2;
    }
    private int leftChild(int k) {
        return k * 2;
    }
    private int rightChild(int k) {
        return k * 2 + 1;
    }
    private void swap(int k, int pk) {
        T tmp_pk = minPQ.get(pk);
        T tmp_k = minPQ.set(k, tmp_pk);
        minPQ.set(pk, tmp_k);

        p_map.replace(tmp_k, pk);
        p_map.replace(tmp_pk, k);
    }


    /** Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return maps.containsKey(item);
    }

    /** Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return minPQ.get(1);
    }

    /** Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        swap(1, size);
        T remove_item = minPQ.remove(size);
        maps.remove(remove_item);
        p_map.remove(remove_item);
        size = size - 1;
        sink(1);
        return remove_item;
    }
    private void sink(int k) {
        if (rightChild(k) <= size) {
            if (maps.get(minPQ.get(rightChild(k))) < maps.get(minPQ.get(leftChild(k))) && maps.get(minPQ.get(rightChild(k))) < maps.get(minPQ.get(k))) {
                swap(k, rightChild(k));
                sink(rightChild(k));
            }
        }
        if (leftChild(k) <= size && maps.get(minPQ.get(leftChild(k))) < maps.get(minPQ.get(k))) {
            swap(k, leftChild(k));
            sink(leftChild(k));
        }
    }

    /** Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }

    /** Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!minPQ.contains(item)) {
            throw new NoSuchElementException();
        }

        maps.replace(item, priority);
        int k = p_map.get(item);
        if (parent(k) != 0 && maps.get(minPQ.get(parent(k))) > maps.get(minPQ.get(k))) {
            swim(k);
        }
        else {
            sink(k);
        }
    }



    /** Prints out a vey basic drawing of the given array of Objects assuming it
     *  is a heap starting at index 1.
     *  @source Josh Hug. */
    public void printSimpleHeapDrawing() {
        ArrayList<T> heap = minPQ;
        int depth = ((int) (Math.log(heap.size()) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < heap.size(); i++) {
            System.out.printf("%d ", heap.get(i));
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

}
