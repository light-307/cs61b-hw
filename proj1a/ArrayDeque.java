public class ArrayDeque<T> {

    private T[] items;
    private int size, nextFirst, nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }


    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = this.get(i);
        }
        items = a;
        nextFirst = a.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        else {
            nextFirst = nextFirst - 1;
        }
    }

    /** Inserts item into the back of the list. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        size += 1;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        }
        else {
            nextLast = nextLast + 1;
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque() {
        int p = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (p == items.length) {
                p = 0;
            }
            System.out.print(items[p] + " ");
            p = p + 1;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (size <= items.length / 4 & items.length >= 16) {
            resize(items.length / 2);
        }

        if (size == 0) {
            return null;
        }
        T item = items[this.firstnum()];
        items[this.firstnum()] = null;
        nextFirst = this.firstnum();
        size -= 1;
        return item;
    }
    public int firstnum() {
        if (nextFirst == items.length - 1) {
            return 0;
        }
        return nextFirst + 1;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (size <= items.length / 4 & items.length >= 16) {
            resize(items.length / 2);
        }

        if (size == 0) {
            return null;
        }
        T item = items[this.lastnum()];
        items[this.lastnum()] = null;
        nextLast = this.lastnum();
        size -= 1;
        return item;
    }
    public int lastnum() {
        if (nextLast == 0) {
            return items.length - 1;
        }
        return nextLast - 1;
    }


    /** Gets the ith item in the list. */
    public T get(int index) {
        if (size == 0 | index >= size) {
            return null;
        }
        int p = this.firstnum() + index;
        if (p < items.length) {
            return items[p];
        }
        return items[p - items.length];
    }

}
