import java.util.*;

/**
 * Created by why on 9/8/15.
 */
public class Solution9 {
    /* 90 */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> item = new LinkedList<Integer>();
        Arrays.sort(nums);
        helper90(nums, 0, item, res);
        return res;
    }
    public void helper90(int[] nums, int start, List<Integer> item, List<List<Integer>> res) {
        if (start == nums.length) {
            res.add(item);
            return;
        }
        List<Integer> item1 = new LinkedList<Integer>(item);
        item1.add(nums[start]);
        helper90(nums, start + 1, item1, res);
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                helper90(nums, i, item, res);
                return;
            }
        }
    }
    public void helper902(int[] nums, int start, List<Integer> item, List<List<Integer>> res) {
        res.add(item);
        for (int i = start; i < nums.length; i ++) {
            if (i == start || nums[i] != nums[i - 1]) {
                List<Integer> item1 = new LinkedList<Integer>(item);
                item1.add(nums[i]);
                helper902(nums, i + 1, item1, res);
            }
        }
    }
    public void helper903(int[] nums, int start, List<Integer> item, List<List<Integer>> res) {
        if (start == nums.length)
            res.add(item);
        else {
            List<Integer> item1 = new LinkedList<Integer>(item);
            item1.add(nums[start]);
            helper903(nums, start + 1, item1, res);
            for (int i = start + 1; i < nums.length - 1; i ++) {
                if (nums[i] != nums[i - 1])
                    helper903(nums, i, item, res);
            }
        }
    }
    /* 91 */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s == "0")
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if (isValidChar(s.substring(0, 1)))
            dp[1] = 1;
        else
            dp[1] = 0;
        for (int i = 2; i < s.length(); i ++) {
            if (isValidChar(s.substring(i - 1, i)))
                dp[i] += dp[i - 1];
            if (isValidChar(s.substring(i - 2, i)))
                dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }
    public boolean isValidChar(String s) {
        if (s.charAt(0) == '0')
            return false;
        int code = Integer.parseInt(s);
        return (code >= 1 && code <= 26);
    }
    /* 92 */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null)
            return head;
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pn = fakeHead;
        for (int i = 0; i < n; i ++) {
            pn = pn.next;
        }
        ListNode pm = fakeHead;
        for (int i = 0; i < m - 1; i ++) {
            pm = pm.next;
        }
        reverse(pm, pn.next);
        return fakeHead.next;
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
    /* 93 */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<String>();
        if (s.length() < 4 || s.length() > 12)
            return res;
        String item = new String();
        helper93(0, item, s, res);
        return res;
    }
    public void helper93(int index, String item, String s, List<String> res) {
        if (index == 3 && isValidIPAddr(s)) {
            res.add(item + s);
        } else {
            for (int i = 0; i < 3 && i < s.length() - 1; i ++) {
                String sub = s.substring(0, i + 1);
                if (isValidIPAddr(sub))
                    helper93(index + 1, item + sub + '.', s.substring(i + 1), res);
            }
        }
    }
    public boolean isValidIPAddr(String s) {
        if (s.charAt(0) == '0')
            return s.equals("0");
        int code = Integer.parseInt(s);
        return (code >= 1 && code <= 255);
    }
    /* 94 */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null)
            return res;
        helper94(root, res);
        return res;
    }
    public void helper94(TreeNode node, List<Integer> res) {
        if (node == null)
            return;
        else {
            helper94(node.left, res);
            res.add(node.val);
            helper94(node.right, res);
        }
    }
    /* 95 */
    public List<TreeNode> generateTrees(int n) {
        return helper95(1, n);
    }
    public List<TreeNode> helper95(int left, int right) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (left > right)
            res.add(null);
        else {
            for (int i = left; i <= right; i ++) {
                List<TreeNode> lefts = helper95(left, i - 1);
                List<TreeNode> rights = helper95(i + 1, right);
                for (int j = 0; j < lefts.size(); j ++) {
                    for (int k = 0; k < rights.size(); k ++) {
                        TreeNode root = new TreeNode(i);
                        root.left = lefts.get(j);
                        root.right = rights.get(k);
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }
    /* 96 */
    public int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i ++) {
            for (int j = 0; j <= n - 1; j ++) {
                dp[i] += dp[j] * dp[(i - 1) - j];
            }
        }
        return dp[n];
    }
    /* 97 */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i < s1.length() && s1.charAt(i) == s3.charAt(i); i ++)
            dp[i][0] = true;
        for (int j = 0; j < s2.length() && s2.charAt(j) == s3.charAt(j); j ++)
            dp[0][j] = true;

        for (int i = 1; i <= s1.length(); i ++) {
            for (int j = 1; j <= s2.length(); j ++) {
                char ch = s3.charAt(i + j - 1);
                if (ch == s1.charAt(i - 1) && dp[i - 1][j])
                    dp[i][j] = true;
                if (ch == s2.charAt(j - 1) && dp[i][j - 1])
                    dp[i][j] = true;
            }
        }
        return dp[s1.length()][s2.length()];
    }
    /* 98 */
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        boolean ans;
        if (root.left != null) {
            TreeNode tempL = root.left;
            while (tempL.right != null) tempL = tempL.right;
            if (tempL.val >= root.val) return false;
        }
        if (root.right != null) {
            TreeNode tempR = root.right;
            while (tempR.left != null) tempR = tempR.left;
            if (tempR.val <= root.val) return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }
    /* 99 */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        inorder(list, root);
        if (list.size() < 2) return;
        TreeNode maxNode = list.get(0);
        if (list.get(1).val < list.get(0).val)
            maxNode = list.get(0);
        else {
            for (int i = 1; i < list.size() - 1; i ++) {
                if (list.get(i - 1).val < list.get(i).val && list.get(i + 1).val < list.get(i).val) {
                    maxNode = list.get(i);
                    break;
                }
            }
        }
        TreeNode minNode = list.get(list.size() - 1);
        if (list.get(list.size() - 1).val < list.get(list.size() - 2).val)
            minNode = list.get(list.size() - 1);
        else {
            for (int i = 1; i < list.size() - 1; i ++) {
                if (list.get(i - 1).val > list.get(i).val && list.get(i + 1).val > list.get(i).val) {
                    minNode = list.get(i);
                }
            }
        }
        int temp = minNode.val;
        minNode.val = maxNode.val;
        maxNode.val = temp;
    }
    public void inorder(List<TreeNode> list, TreeNode node) {
        if (node == null) return;
        inorder(list, node.left);
        list.add(node);
        inorder(list, node.right);
    }
}
