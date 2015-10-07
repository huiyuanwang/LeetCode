import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by why on 9/9/15.
 */
public class Solution11 {
    /* 110 */
    public boolean isBalanced(TreeNode root) {
        return helper110(root) != -1;
    }
    public int helper110(TreeNode node) {
        if (node == null)
            return 0;
        int left = helper110(node.left);
        int right = helper110(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }
    /* 111 */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0)
            return left >= right ? left + 1: right + 1;
        return Math.min(left, right) + 1;
    }
    /* 112 */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        sum -= root.val;
        if (root.left == null && root.right == null)
            return sum == 0;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
    /* 113 */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> item = new LinkedList<Integer>();
        helper113(res, item, root, sum);
        return res;
    }
    public void helper113(List<List<Integer>> res, List<Integer> item, TreeNode node, int sum) {
        if (node == null)
            return;
        item.add(node.val);
        sum -= node.val;
        if (node.left == null && node.right == null) {
            if (sum == 0)
                res.add(new LinkedList<Integer>(item));
        } else {
            if (node.left != null)
                helper113(res, item, node.left, sum);
            if (node.right != null)
                helper113(res, item, node.right, sum);
        }
        sum += node.val;
        item.remove(item.size() - 1);
    }
    /* 114 */
    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p.right != null)
                stack.push(p.right);
            if (p.left != null) {
                p.right = p.left;
                p.left = null;
            } else if (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                p.right = temp;
            }
            p = p.right;
        }
    }
    /* 115 */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= t.length(); j ++)
            dp[0][j] = 0;
        for (int i = 1; i <= s.length(); i ++)
            dp[i][0] = 1;
        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 1; j <= t.length(); j ++) {
                dp[i][j] = dp[i][j - 1];
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }
        return dp[s.length()][t.length()];
    }
    /* 116 */
    public void connect1(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode lastHead = root;
        TreeLinkNode lastLevel = null;
        TreeLinkNode curHead = null;
        TreeLinkNode curLevel = null;
        while (lastHead != null) {
            lastLevel = lastHead;
            while (lastLevel != null) {
                if (curHead == null) {
                    curHead = lastLevel.left;
                    curLevel = curHead;
                } else {
                    curLevel.next = lastLevel.left;
                    curLevel = curLevel.next;
                }
                if (curHead != null) {
                    curLevel.next = lastLevel.right;
                    curLevel = curLevel.next;
                }
                lastLevel = lastLevel.next;
            }
            lastHead = curHead;
            curHead = null;
        }
    }
    /* 117 */
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode lastHead = root;
        TreeLinkNode lastLevel = null;
        TreeLinkNode curHead = null;
        TreeLinkNode curLevel = null;
        while (lastHead != null) {
            lastLevel = lastHead;
            while (lastLevel != null) {
                if (lastLevel.left != null) {
                    if (curHead == null) {
                        curHead = lastLevel.left;
                        curLevel = curHead;
                    } else {
                        curLevel.next = lastLevel.left;
                        curLevel = curLevel.next;
                    }
                }
                if (lastLevel.right != null) {
                    if (curHead == null) {
                        curHead = lastLevel.right;
                        curLevel = curHead;
                    } else {
                        curLevel.next = lastLevel.right;
                        curLevel = curLevel.next;
                    }
                }
                lastLevel = lastLevel.next;
            }
            lastHead = curHead;
            curHead = null;
        }
    }
    /* 118 */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (numRows == 0)
            return res;
        for (int i = 0; i < numRows; i ++) {
            List<Integer> item = new LinkedList<Integer>();
            item.add(1);
            for (int j = 1; j < i; j ++) {
                List<Integer> prev = res.get(i - 1);
                item.add(prev.get(j - 1) + prev.get(j));
            }
            if (i != 0)
                item.add(1);
            res.add(item);
        }
        return res;
    }
    /* 119 */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        if (rowIndex == 0)
            return res;
        for (int i = 0; i < rowIndex; i ++) {
            res.add(0);
        }
        res.set(0, 1);
        for (int i = 1; i <= rowIndex; i ++) {
            res.set(i, 1);
            for (int j = i - 1; j > 0; j --) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }
}
