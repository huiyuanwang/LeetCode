/**
 * Created by why on 9/9/15.
 */
public class Solution27 {
    /* 274 */
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] record = new int[len + 1];
        for (int i = 0; i < len; i ++) {
            if (citations[i] >= len)
                record[len] ++;
            else
                record[i] ++;
        }
        int total = 0;
        for (int i = len; i >= 0; i --) {
            total += record[i];
            if (total >= i)
                return i;
        }
        return 0;
    }
    /* 275 */
    public int hIndex2(int[] citations) {
        int l = 0, r = citations.length - 1;
        int e = citations.length;
        while(l <= r){
            int m =(l + r) / 2;
            if(citations[m] >= citations.length - m){
                if(m - 1 < 0 || citations[m - 1] <= e - m)
                    return e - m;
                else
                    r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return 0;
    }
    /* 278 */
    /* 279 */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i * i <= n; i ++) {
            dp[i * i] = 1;
        }
        for (int i = 1; i <= n; i ++) {
            for (int j = 1; i + j * j <= n; j ++) {
                if (dp[i + j * j] == 0 || dp[i + j * j] > dp[i] + 1)
                    dp[i + j * j] = dp[i] + 1;
            }
        }
        return dp[n];
    }
}
