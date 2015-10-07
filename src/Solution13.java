import java.util.*;

/**
 * Created by why on 9/10/15.
 */
public class Solution13 {
    /* 130 */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i ++) {
            if (board[i][0] == 'O')
                bfs(board, i, 0);
            if (board[i][n - 1] == 'O')
                bfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j ++) {
            if (board[0][j] == 'O')
                bfs(board, 0, j);
            if (board[m - 1][j] == 'O')
                bfs(board, m - 1, j);
        }
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'Y')
                    board[i][j] = 'O';
            }
        }
    }
    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        if (board[i][j] != 'X') {
            board[i][j] = 'Y';
            dfs(board, i + 1, j);
            dfs(board, i - 1, j);
            dfs(board, i, j + 1);
            dfs(board, i, j - 1);
        }
    }
    public void bfs(char[][] board, int i, int j) {
        Queue<Integer> queue = new LinkedList<Integer>();
        check(board, i, j, queue);
        int n = board[0].length;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / n;
            int y = cur % n;
            check(board, x + 1, y, queue);
            check(board, x - 1, y, queue);
            check(board, x, y + 1, queue);
            check(board, x, y - 1, queue);
        }
    }
    public void check(char[][] board, int i, int j, Queue<Integer> queue) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O')
            return;
        queue.offer(i * n + j);
        board[i][j] = 'Y';
    }
    /* 131 */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<List<String>>();
        helper131(0, s, new LinkedList<String>(), res);
        return res;
    }
    public void helper131 (int index, String s, List<String> item, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new LinkedList<String>(item));
            return;
        }
        for (int i = index + 1; i < s.length(); i ++) {
            String temp = s.substring(index, i);
            if (isPalindrome(temp)) {
                item.add(temp);
                helper131(i, s, item, res);
                item.remove(item.size() - 1);
            }
        }
    }
    public boolean isPalindrome (String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) != s.charAt(high))
                return false;
            low ++;
            high --;
        }
        return true;
    }
    /* 132 */
    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int len = s.length();
        boolean[][] record = new boolean[len][len];
        for (int i = 0; i < len; i ++)
            record[i][i] = true;
        for (int i = len - 1; i >= 0; i --) {
            for (int j = i + 1; j < len; j ++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2 || record[i + 1][j - 1]) {
                        record[i][j] = true;
                    }
                }
            }
        }
        int[] cuts = new int[len + 1];
        for (int i = 0; i <= len; i ++)
            cuts[i] = len - i - 1;
        for (int i = len - 1; i >= 0; i --) {
            for (int j = i; j < len; j ++) {
                if (record[i][j]) {
                    cuts[i] = Math.min(cuts[i], cuts[j + 1] + 1);
                }
            }
        }
        return cuts[0];
    }
    /* 133 */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        map.put(node, head);
        queue.offer(node);
        while (! queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            List<UndirectedGraphNode> neighbors = cur.neighbors;
            for (UndirectedGraphNode neighbor: neighbors) {
                if (! map.containsKey(neighbor)) {
                    UndirectedGraphNode one = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, one);
                    map.get(cur).neighbors.add(one);
                    queue.offer(neighbor);
                } else {
                    map.get(cur).neighbors.add(map.get(neighbor));
                }
            }
        }
        return head;
    }
    /* 134 */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;
        int sum = 0, total = 0;
        for (int i = 0; i < gas.length; i ++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                index = i + 1;
                sum = 0;
            }
        }
        if (total < 0)
            return -1;
        else
            return index;
    }
    /* 135 */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int candies[] = new int[len];
        candies[0] = 1;
        for (int i = 1; i < len; i ++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }
        for (int j = len - 2; j >= 0; j --) {
            int cur = 1;
            if (ratings[j] > ratings[j + 1]) {
                cur = candies[j + 1] + 1;
                candies[j] = Math.max(candies[j], cur);
            }
        }
        int min = 0;
        for (int k = 0; k < len; k ++) {
            min += candies[k];
        }
        return min;
    }
    /* 136 */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            res ^= nums[i];
        }
        return res;
    }
    /* 137 */
    public int singleNumber2(int[] nums) {
        int zero = -1, one = 0, two = 0;
        for (int i = 0; i < nums.length; i ++) {
            int newOne = zero & nums[i] | one & ~nums[i];
            int newTwo = one & nums[i] | two & ~nums[i];
            zero = two & nums[i] | zero & ~nums[i];
            one = newOne;
            two = newTwo;
        }
        return one;
    }
    /* 139 */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0)
            return true;
        if (wordDict.size() == 0)
            return false;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i ++) {
            if (!dp[i]) continue;
            for (String match: wordDict) {
                int len = match.length();
                if (i + len > s.length() || dp[i + len]) continue;
                if (s.substring(i, i + len).equals(match))
                    dp[i + len] = true;
            }
        }
        return dp[s.length()];
    }
}
