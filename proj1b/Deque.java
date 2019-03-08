/** Deque interface. */

public interface Deque<T> {
    /** Add an item of type T to the front of the deque. */
    void addFirst(T item);

    /** Add an item of type T to the back of the deque. */
    void addLast(T item);

    /** Return true if deque is empty, false otherwise. */
    boolean isEmpty();

    /** Return the number of items in the deque. */
    int size();

    /** Prints the items in the deque from first to last, separated by a space. */
    void printDeque();

    /** Removes and returns the item at the front of the deque. */
    T removeFirst();

    /** Removes and returns the item at the back of the deque. */
    T removeLast();

    /** Gets the item at the given index, where 0 is the front. */
    T get(int index);

}
