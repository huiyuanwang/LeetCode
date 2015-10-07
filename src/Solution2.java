import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by why on 9/1/15.
 */
public class Solution2 {
    /* 20 */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        if (s.length() % 2 == 1)
            return false;
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.push(s.charAt(i));
            else {
                if (stack.isEmpty())
                    return false;
                char ch = stack.pop();
                if (s.charAt(i) == ')' && ch != '(')
                    return false;
                if (s.charAt(i) == ']' && ch != '[')
                    return false;
                if (s.charAt(i) == '}' && ch != '{')
                    return false;
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }
    /* 21 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode head = new ListNode(0);
        ListNode l3;
        if (l1.val < l2.val) {
            l3 = l1;
            l1 = l1.next;
        } else {
            l3 = l2;
            l2 = l2.next;
        }
        head.next = l3;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 != null)
            l3.next = l1;
        else
            l3.next = l2;
        return head.next;
    }
    /* 22 */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if (n <= 0)
            return res;
        String str = new String();
        dfs(res, str, n, n);
        return res;
    }
    public void dfs (List<String> res, String str, int left, int right) {
        if (left > right)
            return;
        if (left == 0 && right == 0) {
            res.add(new String(str));
            return;
        }
        if (left > 0)
            dfs(res, str + '(', left - 1, right);
        if (right > 0)
            dfs(res, str + ')', left, right - 1);
    }
    /* 23 */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        else
            return binarySort(lists, 0, lists.length - 1);
    }
    public ListNode binarySort(ListNode[] lists, int low, int high) {
        if (low == high)
            return lists[low];
        if (low == high - 1)
            return merge2Lists(lists[low], lists[high]);
        else {
            int mid = (low + high) / 2;
            return merge2Lists(binarySort(lists, low, mid), binarySort(lists, mid + 1, high));
        }
    }
    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode head = new ListNode(0);
        ListNode l3;
        if (l1.val < l2.val) {
            l3 = l1;
            l1 = l1.next;
        } else {
            l3 = l2;
            l2 = l2.next;
        }
        head.next = l3;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 != null)
            l3.next = l1;
        else
            l3.next = l2;
        return head.next;
    }
    /* 24 */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode p1 = newHead;
        ListNode p2 = head;

        while (p1.next != null && p2.next != null) {
            ListNode next = p2.next.next;
            p2.next.next = p2;
            p1.next = p2.next;
            p2.next = next;

            p1 = p2;
            p2 = p2.next;
        }
        return newHead.next;
    }
    /* 25 */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;

        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode p = newHead;
        ListNode q = head;
        int i = 0;
        while (q != null) {
            i ++;
            if (i % k == 0) {
                p = reverse(p, q.next);
                q = p.next;
            } else {
                q = q.next;
            }
        }
        return newHead.next;
    }
    public ListNode reverse(ListNode fakeHead, ListNode next) {
        ListNode p = fakeHead.next;
        while (p.next != next) {
            ListNode q = p.next;
            p.next = q.next;
            q.next = fakeHead.next;
            fakeHead.next = q;
        }
        return p;
    }
    /* 26 */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        int i, j;
        for (j = 0, i = 1; i < nums.length; i ++) {
            if (nums[i] != nums[j]) {
                j ++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
    /* 27 */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int i, j;
        for (i = 0, j = 0; i < nums.length; i ++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j ++;
            }
        }
        return j;
    }
    /* 28 */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;

        for (int i = 0; i < haystack.length(); i ++) {
            if (haystack.length() - i + 1 < needle.length())
                return -1;
            int k = i, j = 0;
            while (j < needle.length() && k < haystack.length() && needle.charAt(j) == haystack.charAt(k)) {
                j ++;
                k ++;
                if (j == needle.length())
                    return i;
            }
        }
        return -1;
    }
    /* 29 */
    public int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        if (divisor == -1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        long dend = Math.abs((long)dividend);
        long sor = Math.abs((long)divisor);
        int res = 0;
        while (dend >= sor) {
            int numShift = 0;
            while (dend >= (sor << numShift)) {
                numShift ++;
            }
            res += 1 << (numShift - 1);
            dend -= (sor << (numShift - 1));
        }

        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
            return res;
        else
            return -res;
    }
}
