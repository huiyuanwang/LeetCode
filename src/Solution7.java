import java.util.*;

/**
 * Created by why on 9/10/15.
 */
public class Solution7 {
    /* 70 */
    public int climbStairs(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i ++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    /* 71 */
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return path;
        Stack<String> stack = new Stack<String>();
        String[] strs = path.split("/");
        for (int i = 0; i < strs.length; i ++) {
            if (strs[i].equals(".") || strs[i].length() == 0)
                continue;
            else if (!strs[i].equals(".."))
                stack.push(strs[i]);
            else if(!stack.isEmpty())
                stack.pop();
        }
        Stack<String> temp = new Stack<String>();
        while (!stack.isEmpty())
            temp.push(stack.pop());
        StringBuilder sb = new StringBuilder();
        if (temp.isEmpty())
            sb.append("/");
        else {
            while (!temp.isEmpty())
                sb.append("/" + temp.pop());
        }
        return sb.toString();
    }
    /* 72 */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i ++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j ++)
            dp[0][j] = j;

        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <= n; j ++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    int insert = dp[i - 1][j] + 1;
                    int delete = dp[i][j - 1] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    int min = Math.min(insert, delete);
                    min = Math.min(min, replace);
                    dp[i][j] = min;
                }
            }
        }
        return dp[m][n];
    }
    /* 73 */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean rowZero = false;
        boolean colZero = false;
        for (int i = 0; i < m; i ++) {
            if (matrix[i][0] == 0) {
                colZero = true;
                break;
            }
        }
        for (int j = 0; j < n; j ++) {
            if (matrix[0][j] == 0) {
                rowZero = true;
                break;
            }
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if (rowZero) {
            for (int j = 0; j < n; j ++)
                matrix[0][j] = 0;
        }
        if (colZero) {
            for (int i = 0; i < m; i ++)
                matrix[i][0] = 0;
        }
    }
    /* 74 */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (target == matrix[row][col])
                return true;
            else if (target > matrix[row][col])
                col ++;
            else
                row --;
        }
        return false;
    }
    /* 75 */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        int left = 0;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0)
                left ++;
            else
                break;
        }
        for (int j = nums.length - 1; j >= 0; j --) {
            if (nums[j] == 2)
                right --;
            else
                break;
        }
        for (int k = left; k <= right; k ++) {
            if (nums[k] == 0) {
                swap(nums, k, left);
                left ++;
            } else if (nums[k] == 2) {
                swap(nums, k, right);
                right --;
                k --;
            }
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    /* 76 */
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0)
            return "";
        HashMap<Character, Integer> standard = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i ++) {
            if (standard.containsKey(t.charAt(i)))
                standard.put(t.charAt(i), standard.get(t.charAt(i)) + 1);
            else
                standard.put(t.charAt(i), 1);
        }
        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
        int count = 0;
        int start = 0;
        int min = s.length() + 1;
        String res = "";
        for (int j = 0; j < s.length(); j ++) {
            if (standard.containsKey(s.charAt(j))) {
                if (hash.containsKey(s.charAt(j))) {
                    if (hash.get(s.charAt(j)) < standard.get(s.charAt(j)))
                        count ++;
                    hash.put(s.charAt(j), hash.get(s.charAt(j)) + 1);
                }
                else {
                    count ++;
                    hash.put(s.charAt(j), 1);
                }
            }
            if (count == t.length()) {
                char test = s.charAt(start);
                while (!hash.containsKey(test) || hash.get(test) > standard.get(test)) {
                    if (hash.containsKey(test) && hash.get(test) > standard.get(test))
                        hash.put(test, hash.get(test) - 1);
                    start ++;
                    test = s.charAt(start);
                }
                if (j - start + 1 < min) {
                    min = j - start + 1;
                    res = s.substring(start, j + 1);
                }
            }
        }
        return res;
    }
    /* 77 */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res =  new LinkedList<List<Integer>>();
        List<Integer> item = new LinkedList<Integer>();
        helper77(1, n, k, item, res);
        return res;
    }
    public void helper77(int start, int n, int count, List<Integer> item, List<List<Integer>> res) {
        if (count == 0)
            res.add(item);
        else if (start <= n){
            List<Integer> item1 = new LinkedList<Integer>(item);
            item1.add(start);
            helper77(start + 1, n, count - 1, item1, res);
            helper77(start + 1, n, count, item, res);
        }
    }
    /* 78 */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> item = new LinkedList<Integer>();
        Arrays.sort(nums);
        helper78(nums, 0, item, res);
        return res;
    }
    public void helper78(int[] nums, int start, List<Integer> item, List<List<Integer>> res) {
        if (start == nums.length)
            res.add(item);
        else {
            List<Integer> item1 = new LinkedList<Integer>(item);
            item1.add(nums[start]);
            helper78(nums, start + 1, item1, res);
            helper78(nums, start + 1, item, res);
        }
    }
    /* 79 */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (helper79(0, board, word, i, j))
                    return true;
            }
        }
        return false;
    }
    public boolean helper79(int index, char[][] board, String word, int i, int j) {
        if (index == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (word.charAt(index) == board[i][j]) {
            char temp = board[i][j];
            board[i][j] = '*';
            boolean res = helper79(index + 1, board, word, i + 1, j)
                    || helper79(index + 1, board, word, i - 1, j)
                    || helper79(index + 1, board, word, i, j + 1)
                    || helper79(index + 1, board, word, i, j - 1);
            board[i][j] = temp;
            return res;
        }
        return false;
    }
}
