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
    /* 233 */
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m, b = n % m;
            ones += (a + 8) / 10 * m;
            if(a % 10 == 1) ones += b + 1;
        }
        return ones;
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
    /* 235 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val > q.val) return lowestCommonAncestor(root, q, p);
        if (root.val > p.val && root.val < q.val) return root;
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
    /* 236 */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }
    /* 237 */
    public void deleteNode(ListNode node) {
        if (node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
    /* 238 */
    public int[] productExceptSelf(int[] nums) {
        long total = 1;
        int countZero = 0;
        int len = nums.length;
        for (int elem : nums) {
            if (elem == 0) {
                countZero ++;
                continue;
            }
            total *= elem;
        }
        if (countZero >= 2) {
            for (int i = 0; i < len; i ++) {
                nums[i] = 0;
            }
        } else if (countZero == 1) {
            for (int i = 0; i < len; i ++) {
                if (nums[i] == 0)
                    nums[i] = (int)total;
                else
                    nums[i] = 0;
            }
        } else {
            for (int i = 0; i < len; i ++) {
                nums[i] = (int)(total / nums[i]);
            }
        }
        return nums;
    }
}
