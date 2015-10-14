import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by why on 10/13/15.
 */
public class Solution22 {
    /* 220 */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i ++) {
            if (set.ceiling(nums[i]) != null && set.ceiling(nums[i]) <= nums[i] + t) return true;
            if (set.floor(nums[i]) != null && nums[i] <= set.floor(nums[i]) + t) return true;
            set.add(nums[i]);
            if (i >= k) set.remove(nums[i - k]);
        }
        return false;
    }
    /* 221 */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i ++) {
            if (matrix[i][0] == '1') dp[i][0] = 1;
        }
        for (int j = 0; j < n; j ++) {
            if (matrix[0][j] == '1') dp[0][j] = 1;
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (matrix[i][j] == '1')
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i -1][j], dp[i][j - 1])) + 1;
                else
                    dp[i][j] = 0;
            }
        }
        int max = 0;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
    /* 222 */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = countLeft(root);
        int right = countRight(root);
        if (left == right) {
            return (1 << left) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
    public int countLeft(TreeNode node) {
        int count = 0;
        while (node != null) {
            count ++;
            node = node.left;
        }
        return count;
    }
    public int countRight(TreeNode node) {
        int count = 0;
        while (node != null) {
            count ++;
            node = node.right;
        }
        return count;
    }
    /* 223 */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int long1 = C - A;
        int wide1 = D - B;
        int long2 = G - E;
        int wide2 = H - F;
        int area = long1 * wide1 + long2 * wide2;
        if (C < E || A > G || D < F || B > H) return area;
        int coverLong = Math.min(C, G) - Math.max(A, E);
        int coverWide = Math.min(D, H) - Math.max(B, F);
        area -= coverLong * coverWide;
        return area;
    }
    /* 226 */
    public TreeNode invertTree(TreeNode root) {
        if (root != null)
            helper(root);
        return root;
    }
    public void helper(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        if (node.left != null)
            helper(node.left);
        if (node.right != null)
            helper(node.right);
    }
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }
    /* 228 */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new LinkedList<String>();
        if (nums == null || nums.length == 0) return res;
        int start = nums[0], cur = nums[0], end = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] == cur + 1) {
                cur ++;
                end ++;
            } else {
                StringBuilder sb = new StringBuilder();
                if (start == end) {
                    sb.append(start);
                } else {
                    sb.append(start).append("->").append(end);
                }
                res.add(sb.toString());
                start = nums[i];
                cur = start;
                end = start;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (start == end) {
            sb.append(start);
        } else {
            sb.append(start).append("->").append(end);
        }
        res.add(sb.toString());
        return res;
    }

}

