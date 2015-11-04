import java.util.Iterator;

/**
 * Created by why on 10/18/15.
 */
public class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    boolean getPeek = false;
    Integer peekVal = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (! getPeek) {
            getPeek = true;
            peekVal = iterator.next();
        }
        return peekVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (! getPeek) {
            return iterator.next();
        }
        Integer res = peekVal;
        getPeek = false;
        peekVal = null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return getPeek || iterator.hasNext();
    }
}
