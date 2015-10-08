import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by why on 10/7/15.
 */
public class Solution17 {
    /* 171 */
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            res = res * 26 + ch + 1 - 'A';
        }
        return res;
    }
    /* 172 */
    public int trailingZeroes(int n) {
        int res = 0;
        while (n >= 5) {
            n /= 5;
            res += n;
        }
        return res;
    }
    /* 174 */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

        for (int i = m - 2; i >= 0; i --) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int j = n - 2; j >= 0; j --) {
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }
        for (int i = m - 2; i >= 0; i --) {
            for (int j = n - 2; j >= 0; j --) {
                int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                dp[i][j] = Math.min(down, right);
            }
        }
        return dp[0][0];
    }
    /* 179 */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        List<String> strs = new LinkedList<>();
        for (int num : nums) {
            strs.add(String.valueOf(num));
        }
        Collections.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        StringBuilder res = new StringBuilder();
        for (String str: strs) {
            res.append(str);
        }
        while(res.charAt(0)=='0' && res.length() > 1) {
            res.deleteCharAt(0);
        }
        return res.toString();
    }
}
