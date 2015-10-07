import java.util.LinkedList;
import java.util.List;

/**
 * Created by why on 9/8/15.
 */
public class Solution6 {
    /* 60 */
    public String getPermutation(int n, int k) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= n; i ++)
            list.add(i);

        int factorial = 1;
        for (int i = 2; i < n; i ++)
            factorial *= i;

        StringBuilder sb = new StringBuilder();
        k --;
        for (int i = n - 1; i >= 0; i --) {
            int index = k / factorial;
            sb.append(list.remove(index));

            k = k % factorial;
            if (i != 0)
                factorial = factorial / i;
        }
        return sb.toString();
    }
    /* 61 */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len ++;
            p = p.next;
        }
        ListNode p1 = fakeHead;
        k %= len;
        for (int i = 0; i < k && p1 != null; i ++)
            p1 = p1.next;
        ListNode p2 = fakeHead;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p2 != fakeHead) {
            p1.next = fakeHead.next;
            fakeHead.next = p2.next;
            p2.next = null;
        }
        return fakeHead.next;
    }
    /* 62 */
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        int[][] record = new int[m][n];
        for (int i = 0; i < m; i ++)
            record[i][0] = 1;
        for (int j = 0; j < n; j ++)
            record[0][j] = 1;
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                record[i][j] = record[i - 1][j] + record[i][j - 1];
            }
        }
        return record[m - 1][n - 1];
    }
    /* 63 */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 0 || n == 0)
            return 0;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i ++) {
            if (obstacleGrid[i][0] == 1)
                dp[i][0] = 0;
            else
                dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j ++) {
            if (obstacleGrid[0][j] == 1)
                dp[0][j] = 0;
            else
                dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    /* 64 */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0 || n == 0)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i ++)
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        for (int j = 1; j < n; j ++)
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }
    /* 65 */
    public boolean isNumber(String s) {
        if(s.trim().isEmpty()){
            return false;
        }
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        if(s.trim().matches(regex)){
            return true;
        }else{
            return false;
        }
    }
    /* 66 */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return null;
        int len = digits.length - 1;
        for (int i = len; i >= 0; i --) {
            if (digits[i] < 9) {
                digits[i] ++;
                break;
            } else {
                digits[i] = 0;
            }
        }
        if (digits[0] == 0) {
            int[] ds = new int[digits.length + 1];
            ds[0] = 1;
            for (int i = 0; i <= len; i ++) {
                ds[i + 1] = digits[i];
            }
            return ds;
        } else
            return digits;
    }
    /* 67 */
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        int carry = 0;
        String res = "";
        int maxLen = Math.max(m, n);
        for (int i = 0; i < maxLen; i++) {
            int p = 0, q = 0;
            if(i < m)
                p = a.charAt(m - 1 - i) - '0';
            else
                p = 0;

            if(i < n)
                q = b.charAt(n - 1 - i) - '0';
            else
                q = 0;

            int tmp = p + q + carry;
            carry = tmp / 2;
            res = tmp % 2 + res;
        }
        return (carry == 0) ? res : "1" + res;
    }
    /* 68 */

    /* 69 */
    public int mySqrt(int x) {
        int low = 0;
        int high = x;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid * mid == x)
                return mid;
            else if (mid * mid < x) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return high;
    }
}
