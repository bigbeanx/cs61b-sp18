/** Build a linked list deque. */
public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private TNode sentinel;

    /** Node object in the linked list. */
    public class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        public TNode() {
            item = null;
            prev = null;
            next = null;
        }

        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /** Creat an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Add an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        size = size + 1;
        TNode newNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        size = size + 1;
        TNode newNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    /** Return true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            return;
        }

        TNode currentNode = sentinel.next;
        while (currentNode.item != null) {
            System.out.print(currentNode.item);
            System.out.print(" ");
            currentNode = currentNode.next;
        }
    }

    /** Removes and returns the item at the front of the deque. */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size = size - 1;

        TNode removeNode = sentinel.next;
        T returnItem = removeNode.item;
        sentinel.next = removeNode.next;
        removeNode.next.prev = sentinel;
        removeNode = null;
        return returnItem;
    }

    /** Removes and returns the item at the back of the deque. */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size = size - 1;

        TNode removeNode = sentinel.prev;
        T returnItem = removeNode.item;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        removeNode = null;
        return returnItem;
    }

    /** Gets the item at the given index, where 0 is the front. */
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        TNode ptr = sentinel.next;

        while (index > 0) {
            ptr = ptr.next;
            index = index - 1;
        }
        return ptr.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }

        return getHelper(index, sentinel.next);

    }

    /** Helper method for getRecursive. */
    private T getHelper(int index, TNode ptr) {
        if (index == 0) {
            return ptr.item;
        }

        return getHelper(index - 1, ptr.next);
    }
}
