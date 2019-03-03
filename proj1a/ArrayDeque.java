/** Build an array deque. */
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        items = (T[]) new Object[8];
    }

    /** Find the previous index of given index in current array deque. */
    private int findPrevious(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    /** Find the next index of given index in current array deque. */
    private int findNext(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (this.isFull()) {
            this.expandArray();
        }
        size = size + 1;
        items[nextFirst] = item;
        nextFirst = findPrevious(nextFirst);
    }

    /** Add an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (this.isFull()) {
            this.expandArray();
        }
        size = size + 1;
        items[nextLast] = item;
        nextLast = findNext(nextLast);
    }

    /** Return true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (this.isEmpty()) {
            return;
        }

        int currentIndex = findNext(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex]);
            System.out.print(" ");
            currentIndex = findNext(currentIndex);
        }
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (this.isSparse()) {
            this.shrinkArray();
        }
        size = size - 1;
        nextFirst = findNext(nextFirst);
        T firstItem = items[nextFirst];
        items[nextFirst] = null;
        return firstItem;
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (this.isSparse()) {
            this.shrinkArray();
        }
        size = size - 1;
        nextLast = findPrevious(nextLast);
        T lastItem = items[nextLast];
        items[nextLast] = null;
        return lastItem;
    }

    /** Gets the item at the given index, where 0 is the front. */
    public T get(int index) {
        int idx = findNext(nextFirst) + index;
        if (idx >= items.length) {
            idx = idx - items.length;
        }
        return items[idx];
    }

    /** Return true if array is full, false otherwise. */
    private boolean isFull() {
        return size == items.length;
    }

    /** Expand current array size to be doubled supposing current array is full. */
    private void expandArray() {
        T[] newArray = (T[]) new Object[2 * items.length];
        int firstIndex = findNext(nextFirst);
        System.arraycopy(items, firstIndex, newArray, 0, items.length - firstIndex);
        System.arraycopy(items, 0, newArray, items.length - firstIndex, firstIndex);
        nextFirst = newArray.length - 1;
        nextLast = size;
        items = newArray;
    }

    /** Return true if array need to be shrunk, false otherwise. */
    private boolean isSparse() {
        if (items.length < 16) {
            return false;
        }

        double usageFactor = ((double) size) / items.length;
        return usageFactor < 0.25;
    }

    /** Shrink current array size to be halved supposing current array is sparse. */
    private void shrinkArray() {
        T[] newArray = (T[]) new Object[items.length / 2];
        int firstIndex = findNext(nextFirst);
        int lastIndex = findPrevious(nextLast);

        if (firstIndex < lastIndex) {
            System.arraycopy(items, firstIndex, newArray, 0, size);
        } else {
            System.arraycopy(items, firstIndex, newArray, 0, items.length - firstIndex);
            System.arraycopy(items, 0, newArray, items.length - firstIndex, lastIndex + 1);
        }

        nextFirst = newArray.length - 1;
        nextLast = size;
        items = newArray;
    }
}
