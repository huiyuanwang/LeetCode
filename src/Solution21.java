import java.util.*;

/**
 * Created by why on 10/8/15.
 */
public class Solution21 {
    /* 210 */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return null;
        int[] res = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i ++) {
                res[i] = i;
            }
            return res;
        }
        int[] record = new int[numCourses];
        int index = 0;
        for (int[] prerequisite : prerequisites) {
            record[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i ++) {
            if (record[i] == 0) {
                queue.offer(i);
                res[index] = i;
                index ++;
            }
        }
        while (! queue.isEmpty()) {
            int course = queue.poll();
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == course) {
                    record[prerequisite[0]]--;
                    if (record[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                        res[index] = prerequisite[0];
                        index ++;
                    }
                }
            }
        }
        if (index == numCourses) return res;
        else return new int[0];
    }
    /* 212 */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        if (words == null || words.length == 0) return res;
        Trie trie = new Trie();
        for (String word: words) {
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                helper(board, "", i, j, trie, res);
            }
        }
        return res;
    }
    public void helper(char[][] board, String word, int i, int j, Trie trie, List<String> res) {
        int m = board.length - 1, n = board[0].length - 1;
        if (i < 0 || i > m || j < 0 || j > n) return;
        word += board[i][j];
        if (! trie.startsWith(word)) return;
        if (trie.search(word)) res.add(word);
        char temp = board[i][j];
        board[i][j] = '*';
        helper(board, word, i + 1, j, trie, res);
        helper(board, word, i - 1, j, trie, res);
        helper(board, word, i, j + 1, trie, res);
        helper(board, word, i, j - 1, trie, res);
        board[i][j] = temp;
    }
    /* 213 */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];

        /* Do not include the last element */
        int[] dl = new int[nums.length];
        dl[0] = 0;
        dl[1] = nums[0];
        for (int i = 1; i < nums.length - 1; i ++) {
            dl[i + 1] = Math.max(dl[i], dl[i - 1] + nums[i]);
        }

        /* Do not include the first element */
        int[] dh = new int[nums.length];
        dh[0] = 0;
        dh[1] = nums[1];
        for (int i = 2; i < nums.length; i ++) {
            dh[i] = Math.max(dh[i - 1], dh[i - 2] + nums[i]);
        }

        return Math.max(dl[nums.length - 1], dh[nums.length - 1]);
    }
    /* 216 */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        helper(n, k, 1, new LinkedList<Integer>(), res);
        return res;
    }
    public void helper(int target, int num, int index, List<Integer> item, List<List<Integer>> res) {
        if (target < 0 || index > 10) return;
        if (num == 0 && target == 0) {
            res.add(new LinkedList<Integer>(item));
            return;
        } else if (num == 0) return;

        helper(target, num, index + 1, new LinkedList<Integer>(item), res);
        target -= index;
        item.add(index);
        helper(target, num - 1, index + 1, item, res);
    }
    /* 217 */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int elem: nums) {
            if (set.contains(elem)) return true;
            else set.add(elem);
        }
        return false;
    }
    /* 219 */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
