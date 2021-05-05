public class LinkedListDeque<T> {

    private DNode sentinel;
    private int size;

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new DNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new DNode(sentinel, item, sentinel.next);
        if (sentinel.prev == sentinel) {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev.next = new DNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = sentinel.next.val;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = sentinel.prev.val;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (sentinel.next == sentinel | index >= size) {
            return null;
        }
        DNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.val;
    }

    public T getRecursive(int index) {
        if (size == 0 | index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
    public T getRecursive(int index, DNode p) {
        if (index == 0) {
            return p.val;
        }
        return getRecursive(index - 1, p.next);
    }


    private class DNode {
        /**
         * Previous DNode.
         */
        public DNode prev;

        /**
         * Next DNode.
         */
        public DNode next;

        /**
         * Value contained in DNode.
         */
        public T val;

        /**
         * @param v the item to be placed in DNode.
         */
        public DNode(T v) {
            this(null, v, null);
        }

        /**
         * @param p previous DNode.
         * @param v  value to be stored in DNode.
         * @param n next DNode.
         */
        public DNode(DNode p, T v, DNode n) {
            this.prev = p;
            this.val = v;
            this.next = n;
        }
    }

}
