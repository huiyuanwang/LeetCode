import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by why on 10/14/15.
 */
public class ArrayIterator {
    private int[][] arrs;
    private int m;
    private int n;
    private int i;
    private int j;
    private int oldN;
    private int oldI;
    private int oldJ;

    private boolean hasNext = false;
    private boolean hasPrev = false;

    public ArrayIterator(int[][] arrs) {
        this.arrs = arrs;
        m = arrs.length;
        i = 0;
        j = 0;
        oldI = -1;
        oldJ = -1;
        oldN = -1;
        while (i < m) {
            n = arrs[i].length;
            if (n > 0) {
                hasNext = true;
                break;
            }
            i ++;
        }
    }
    public boolean hasNext() {
        return hasNext;
    }
    public int next() throws NoSuchElementException {
        oldN = n;
        oldI = i;
        oldJ = j;
        hasPrev = hasNext;

        if (! hasNext) {
            throw new NoSuchElementException();
        }
        int res = arrs[i][j];
        hasNext = false;
        if (j < n - 1) {
            j ++;
            hasNext = true;
        } else {
            j = 0;
            i ++;
            while (i < m) {
                n = arrs[i].length;
                if (n > 0) {
                    hasNext = true;
                    break;
                }
                i ++;
            }
        }
        return res;
    }
    public void remove() throws IllegalArgumentException {
        if (! hasPrev) {
            throw new IllegalArgumentException();
        }
        hasPrev = false;
        int[] newArr = new int[n - 1];
        int index = 0;
        while (index < oldJ) {
            newArr[index] = arrs[oldI][index];
            index ++;
        }
        while (index < oldN - 1) {
            newArr[index] = arrs[oldI][index + 1];
            index ++;
        }
        arrs[oldI] = newArr;
        if (i == oldI) {
            j --;
            n = arrs[i].length;
        }
    }
}
