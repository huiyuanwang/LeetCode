import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by why on 10/13/15.
 */
public class Solution23 {
    /* 230 */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        int res = 0;
        while (node != null || ! stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                k --;
                if (k == 0) {
                    res = node.val;
                    break;
                }
                node = node.right;
            }
        }
        return res;
    }
    /* 231 */
    public boolean isPowerOfTwo(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 0x1;
            n = n >>> 1;
        }
        return count == 1;
    }
    /* 234 */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode slow = fakeHead, fast = fakeHead;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = reverse(slow, null);
        slow.next = null;
        slow = head;
        while (slow != null) {
            if (fast.val != slow.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
    public ListNode reverse(ListNode fakeHead, ListNode next) {
        ListNode p = fakeHead.next;
        while (p.next != next) {
            ListNode q = p.next;
            p.next = q.next;
            q.next = fakeHead.next;
            fakeHead.next = q;
        }
        return fakeHead.next;
    }
}
