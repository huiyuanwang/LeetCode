import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by why on 10/8/15.
 */
public class Solution19 {
    /* 190 */
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans = (ans << 1) | (((1 << i) & n) >>> i);
        }
        return ans;
    }
    /* 191 */
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i ++) {
            res += ((1 << i) & n) >>> i;
        }
        return res;
    }
    /* 192 */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return dp[nums.length];
    }
    /* 198 */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(null);
        TreeNode prev = null;
        while (! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                res.add(prev.val);
                if (! queue.isEmpty())
                    queue.offer(null);
            } else {
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                prev = cur;
            }
        }
        return res;
    }
}
