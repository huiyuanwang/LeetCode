import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

/**
 * Created by why on 10/16/15.
 */
public class Counter {
    Queue<Long> hits = new LinkedList<>();
    long curTimeStamp;
    Timestamp ts;

    public void clearUp() {
        curTimeStamp = ts.getTime();
        while (! hits.isEmpty() && hits.peek() - curTimeStamp > 900) {
            hits.poll();
        }
    }

    public void hit() {
        clearUp();
        hits.offer(curTimeStamp);
    }

    public int getLog() {
        clearUp();
        return hits.size();
    }
}
