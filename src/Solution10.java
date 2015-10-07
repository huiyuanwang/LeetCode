import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by why on 9/8/15.
 */
public class Solution10 {
    /* 100 */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;
        else {
            if (p.val == q.val)
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            else
                return false;
        }
    }
    /* 101 */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return compare(root.left, root.right);
    }
    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val == right.val)
            return compare(left.left, right.right) && compare(left.right, right.left);
        else
            return false;
    }
    /* 102 */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(null);
        List<Integer> item = new LinkedList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                res.add(new LinkedList<Integer>(item));
                item.clear();
                if (!queue.isEmpty())
                    queue.offer(null);
            } else {
                item.add(temp.val);
                if (temp.left != null)
                    queue.offer(temp.left);
                if (temp.right != null)
                    queue.offer(temp.right);
            }
        }
        return res;
    }
    /* 103 */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(null);
        boolean left = false;
        Stack<Integer> stack = new Stack<Integer>();
        List<Integer> item = new LinkedList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                if (left) {
                    while (!item.isEmpty())
                        stack.push(item.remove(0));
                    while (!stack.isEmpty())
                        item.add(stack.pop());
                }
                res.add(new LinkedList<Integer>(item));
                item.clear();
                if (!queue.isEmpty())
                    queue.offer(null);
                left = !left;
            } else {
                item.add(temp.val);
                if (temp.left != null)
                    queue.offer(temp.left);
                if (temp.right != null)
                    queue.offer(temp.right);
            }
        }
        return res;
    }
    /* 104 */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    /* 105 */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return construct105(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    public TreeNode construct105(int[] preorder, int prestart, int preend, int[] inorder, int instrat, int inend) {
        if (prestart > preend || instrat > inend)
            return null;
        int val = preorder[prestart];
        TreeNode node = new TreeNode(val);
        int k = 0;
        for (int i = 0; i < inorder.length; i ++){
            if (inorder[i] == val) {
                k = i;
                break;
            }
        }
        node.left = construct105(preorder, prestart + 1, prestart + (k - instrat), inorder, instrat, k - 1);
        node.right = construct105(preorder, prestart + (k - instrat) + 1, preend, inorder, k + 1, inend);
        return node;
    }
    /* 106 */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return construct106(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    public TreeNode construct106(int[] inorder, int instart, int inend,
                               int[] postorder, int poststrat, int postend) {
        if (instart > inend || poststrat > postend)
            return null;
        int val = postorder[postend];
        TreeNode node = new TreeNode(val);
        int k = 0;
        for (int i = 0; i < inorder.length; i ++){
            if (inorder[i] == val) {
                k = i;
                break;
            }
        }
        node.left = construct106(inorder, instart, k - 1, postorder, poststrat, poststrat + k - instart - 1);
        node.right = construct106(inorder, k + 1, inend, postorder, poststrat + k - instart, postend - 1);
        return node;
    }
    /* 107 */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(null);
        List<Integer> item = new LinkedList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                res.add(new LinkedList<Integer>(item));
                item.clear();
                if (!queue.isEmpty())
                    queue.offer(null);
            } else {
                item.add(temp.val);
                if (temp.left != null)
                    queue.offer(temp.left);
                if (temp.right != null)
                    queue.offer(temp.right);
            }
        }
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        while (!res.isEmpty())
            stack.push(res.remove(0));
        while (!stack.isEmpty())
            res.add(stack.pop());
        return res;
    }
    /* 108 */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return construct108(nums, 0, nums.length - 1);
    }
    public TreeNode construct108(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = construct108(nums, start, mid - 1);
        node.right = construct108(nums, mid + 1, end);
        return node;
    }
    /* 109 */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count ++;
        }
        List<ListNode> list = new LinkedList<ListNode>();
        list.add(head);
        return construct109(list, 0, count - 1);
    }
    public TreeNode construct109(List<ListNode> list, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode l = construct109(list, left, mid - 1);
        TreeNode root = new TreeNode(list.get(0).val);
        root.left = l;
        list.set(0, list.get(0).next);
        root.right = construct109(list, mid + 1, right);
        return root;
    }
}
