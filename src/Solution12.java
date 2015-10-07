import java.util.*;

/**
 * Created by why on 9/10/15.
 */
public class Solution12 {
    /* 120 */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null)
            return 0;
        if (triangle.size() == 1)
            return triangle.get(0).get(0);
        int[] dp = new int[triangle.size()];

        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i ++)
            dp[i] = triangle.get(triangle.size() - 1).get(i);

        for (int i = triangle.size() - 2; i >= 0; i --) {
            for (int j = 0; j < triangle.get(i).size(); j ++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
    /* 121 */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int curMin = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i ++) {
            res = Math.max(res, prices[i] - curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        return res;
    }
    /* 122 */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int res = 0;
        for (int i = 1; i < prices.length; i ++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0)
                res += diff;
        }
        return res;
    }
    /* 123 */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int[] left = new int[prices.length], right = new int[prices.length];
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i ++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int j = prices.length - 2; j >= 0; j --) {
            max = Math.max(max, prices[j]);
            right[j] = Math.max(right[j + 1], max - prices[j]);
        }
        int res = 0;
        for (int k = 0; k < prices.length; k ++) {
            res = Math.max(res, left[k] + right[k]);
        }
        return res;
    }
    /* 124 */
    public int maxPathSum(TreeNode root) {
        int[] record = {Integer.MIN_VALUE};
        helper124(root, record);
        return record[0];
    }
    public int helper124(TreeNode node, int record[]) {
        if (node == null) return 0;
        int left = helper124(node.left, record);
        int right = helper124(node.right, record);

        int crossRoot = left + node.val + right;
        int subtree = Math.max(node.val, Math.max(left, right) + node.val);
        record[0] = Math.max(record[0], Math.max(crossRoot, subtree));
        return subtree;
    }
    /* 125 */
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() == 0) return true;
        s = s.trim().toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !isAlphanumeric(s.charAt(left)))
                left ++;
            while (left < right && !isAlphanumeric(s.charAt(right)))
                right --;
            if (s.charAt(left) != s.charAt(right))
                return false;
            left ++;
            right --;
        }
        return true;
    }
    public boolean isAlphanumeric(Character ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            return true;
        if (ch >= '0' && ch <= '9')
            return true;
        else
            return false;
    }
    /* 126 */
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<Set<String>> layers = new LinkedList<Set<String>>();
        Set<String> one = new HashSet<String>();
        one.add(beginWord);
        if (wordList.contains(beginWord)) wordList.remove(beginWord);
        layers.add(one);
        boolean found = false;
        while (!found && layers.get(layers.size() - 1).size() != 0) {
            Set<String> layer = new HashSet<String>();
            Iterator<String> iterator = layers.get(layers.size() - 1).iterator();
            while (iterator.hasNext()) {
                String cur = iterator.next();
                char[] temp = cur.toCharArray();
                for (int i = 0; i < temp.length; i ++) {
                    char old = temp[i];
                    for (char ch = 'a'; ch <= 'z'; ch ++) {
                        if (ch == old)
                            continue;
                        temp[i] = ch;
                        String next = new String(temp);
                        if (next.equals(endWord)) found = true;
                        if (wordList.contains(next)) {
                            layer.add(next);
                            wordList.remove(next);
                        }
                    }
                    temp[i] = old;
                }
            }
            layers.add(layer);
        }
        List<List<String>> res = new LinkedList<List<String>>();
        if (found) {
            List<String> list = new LinkedList<String>();
            list.add(endWord);
            dfs126(beginWord, endWord, layers.size() - 2, list, layers, res);
        }
        return res;
    }
    public void dfs126(String start, String s, int index, List<String> item, List<Set<String>> layers, List<List<String>> res) {
        if (s.equals(start)) {
            List<String> temp = new LinkedList<String>(item);
            Collections.reverse(temp);
            res.add(temp);
            return;
        }
        if (index < 0) return;
        char[] temp = s.toCharArray();
        for (int i = 0; i < s.length(); i ++) {
            char old = temp[i];
            for (char ch = 'a'; ch <= 'z'; ch ++) {
                if (ch == old) continue;
                temp[i] = ch;
                String next = new String(temp);
                if (layers.get(index).contains(next)) {
                    item.add(next);
                    dfs126(start, next, index - 1, item, layers, res);
                    item.remove(item.size() - 1);
                }
            }
            temp[i] = old;
        }
    }
    /* 127 */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) return 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        queue.offer(null);
        int step = 1;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur == null) {
                step ++;
                if (!queue.isEmpty())
                    queue.offer(null);
                continue;
            }
            char[] temp = cur.toCharArray();
            for (int i = 0; i < temp.length; i ++) {
                char old = temp[i];
                for (char ch = 'a'; ch <= 'z'; ch ++) {
                    if (ch == old)
                        continue;
                    temp[i] = ch;
                    String next = new String(temp);
                    if (next.equals(endWord))
                        return step + 1;
                    if (wordList.contains(next)) {
                        queue.offer(next);
                        wordList.remove(next);
                    }
                }
                temp[i] = old;
            }
        }
        return 0;
    }
    /* 128 */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> record = new HashSet<Integer>();
        for (int item: nums)
            record.add(item);
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (!record.contains(nums[i]))
                continue;
            int cur = 1;
            record.remove(nums[i]);
            int left = nums[i] - 1;
            while (record.contains(left)) {
                record.remove(left);
                cur ++;
                left --;
            }
            int right = nums[i] + 1;
            while (record.contains(right)) {
                record.remove(right);
                cur ++;
                right ++;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
    /* 129 */
    public int sumNumbers(TreeNode root) {
        int[] sum = new int[1];
        helper129(root, 0, sum);
        return sum[0];
    }
    public void helper129(TreeNode node, int num, int[] sum) {
        if(node == null) return;
        num = num * 10 + node.val;

        if (node.left == null && node.right == null) {
            sum[0] += num;
            return;
        }
        helper129(node.left, num, sum);
        helper129(node.right, num, sum);
    }
}
