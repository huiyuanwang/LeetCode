import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by why on 10/13/15.
 */
public class Solution23 {

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
