import java.util.*;

/**
 * Created by why on 9/24/15.
 */
public class Solution14 {
    /* 140 */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return helper(new ArrayList[s.length()], s, wordDict, s.length() - 1);
    }
    public List<String> helper(List[] dp, String s, Set<String> dict, int index) {
        if (dp[index] != null) return dp[index];
        List<String> list = new ArrayList<String>();
        for (int i = index; i >= 0; i --) {
            String test = s.substring(i, index + 1);
            if (dict.contains(test)) {
                if (i == 0)
                    list.add(test);
                else {
                    List<String> prev = helper(dp, s, dict, i - 1);
                    for (String item: prev) {
                        list.add(item + ' ' + test);
                    }
                }
            }
        }
        dp[index] = list;
        return list;
    }
    /* 141 */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
    /* 142 */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast.next == null || fast.next.next == null) return null;
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    /* 143 */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        reverse(slow, null);
        ListNode half = slow.next;
        slow.next = null;
        fast = head;
        while (half != null) {
            ListNode temp1 = fast.next, temp2 = half.next;
            fast.next = half;
            half.next = temp1;
            fast = temp1;
            half = temp2;
        }
    }
    public void reverse(ListNode fakeHead, ListNode next) {
        ListNode p = fakeHead.next;
        while (p.next != next) {
            ListNode q = p.next;
            p.next = q.next;
            q.next = fakeHead.next;
            fakeHead.next = q;
        }
    }
    /* 144 */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (! stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }
    /* 145 */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode prev = null;
        while (! stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (prev == null || prev.left == cur || prev.right == cur) {
                if (cur.left != null) stack.push(cur.left);
                else if (cur.right != null) stack.push(cur.right);
                else {
                    res.add(cur.val);
                    stack.pop();
                }
            } else if (cur.left == prev) {
                if (cur.right != null) stack.push(cur.right);
                else {
                    res.add(cur.val);
                    stack.pop();
                }
            } else if (cur.right == prev) {
                res.add(cur.val);
                stack.pop();
            }
            prev = cur;
        }
        return res;
    }
    /* 147 */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode ptr = head.next, last = head;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        while (ptr != null) {
            ListNode prev = fakeHead, cur = fakeHead.next;
            while (cur != ptr && cur.val <= ptr.val) {
                prev = cur;
                cur = cur.next;
            }
            if (cur != ptr) {
                last.next = ptr.next;
                ptr.next = cur;
                prev.next = ptr;
            } else {
                last = ptr;
            }
            ptr = last.next;
        }
        return fakeHead.next;
    }
    /* 148 */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode half = slow.next;
        slow.next = null;
        ListNode left = sortList(head), right = sortList(half);
        return merge2SortedList(left, right);
    }
    public ListNode merge2SortedList(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        ListNode fakeHead = new ListNode(-1), ptr = fakeHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                ptr.next = head1;
                ptr = ptr.next;
                head1 = head1.next;
            } else {
                ptr.next = head2;
                ptr = ptr.next;
                head2 = head2.next;
            }
        }
        if (head1 != null) ptr.next = head1;
        if (head2 != null) ptr.next = head2;
        return fakeHead.next;
    }

}
