import java.util.Stack;

/**
 * Created by why on 10/7/15.
 */
/* 155 */
public class MinStack {
    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) minStack.push(x);
    }

    public void pop() {
        if (! minStack.isEmpty() && minStack.peek().equals(stack.peek())) minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (! minStack.isEmpty()) return minStack.peek();
        return Integer.MAX_VALUE;
    }
}
