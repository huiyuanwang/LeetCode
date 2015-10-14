import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by why on 10/13/15.
 */
public class MyStack {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();
    // Push element x onto stack.
    public void push(int x) {
        if (empty()) {
            queue1.offer(x);
            return;
        }
        if (queue1.isEmpty()) {
            queue1.offer(x);
            while (! queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
        } else if (queue2.isEmpty()) {
            queue2.offer(x);
            while (! queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (! queue1.isEmpty())
            queue1.poll();
        else if (! queue2.isEmpty())
            queue2.poll();
    }

    // Get the top element.
    public int top() {
        if (! queue1.isEmpty())
            return queue1.peek();
        if (! queue2.isEmpty())
            return queue2.peek();
        else
            return 0;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
