package es.datastructur.synthesizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int cap;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        cap = capacity;
    }

    @Override
    public int capacity() {
        return cap;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (fillCount == cap) {
            throw new RuntimeException("Ring Buffer overflow");
        }

        rb[last] = x;
        if (last == cap - 1) {
            last = 0;
        }
        else {
            last = last + 1;
        }
        fillCount = fillCount + 1;
    }

    @Override
    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }

        T temp = rb[first];
        rb[first] = null;
        if (first == cap - 1) {
            first = 0;
        }
        else {
            first = first + 1;
        }
        fillCount = fillCount - 1;
        return temp;
    }

    @Override
    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }


    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;

        public ArrayRingBufferIterator() {
            wizPos = first;
        }

        public boolean hasNext() {
            return wizPos != last - 1;
        }

        public T next() {
            T returnItem = rb[wizPos];
            if (wizPos == cap - 1) {
                wizPos = 0;
            }
            else {
                wizPos += 1;
            }
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayRingBuffer<?> that = (ArrayRingBuffer<?>) o;
        return first == that.first && last == that.last && fillCount == that.fillCount && cap == that.cap && Arrays.equals(rb, that.rb);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(first, last, fillCount, cap);
        result = 31 * result + Arrays.hashCode(rb);
        return result;
    }
}
