import java.util.*;

/**
 * Created by why on 9/6/15.
 */
public class Solution4 {
    /* 40 */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper40(candidates, target, 0, item, res);
        return res;
    }

    public void helper40(int[] candidates, int target, int index, List<Integer> item,
                         List<List<Integer>> res) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new LinkedList<Integer>(item));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (i == index || candidates[i] != candidates[i - 1]) {
                    int newTarget = target - candidates[i];
                    item.add(candidates[i]);
                    helper40(candidates, newTarget, i + 1, item, res);
                    item.remove(item.size() - 1);
                }
            }
        }
    }

    /* 41 */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length && nums[i] > 0 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /* 42 */
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int i, max, total = 0;
        int left[] = new int[height.length];
        int right[] = new int[height.length];
        // from left to right
        left[0] = height[0];
        max = height[0];
        for (i = 1; i < height.length; i++) {
            left[i] = Math.max(max, height[i]);
            max = Math.max(max, height[i]);
        }
        // from right to left
        right[height.length - 1] = height[height.length - 1];
        max = height[height.length - 1];
        for (i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(max, height[i]);
            max = Math.max(max, height[i]);
        }
        // trapped water (when i==0, it cannot trapped any water)
        for (i = 1; i < height.length - 1; i++) {
            int bit = Math.min(left[i], right[i]) - height[i];
            if (bit > 0)
                total += bit;
        }
        return total;
    }

    /* 43 */
    public String multiply(String num1, String num2) {
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        int[] d = new int[num1.length() + num2.length()];
        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                d[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < d.length; i++) {
            int mod = d[i] % 10;
            int carry = d[i] / 10;
            if (i + 1 < d.length) {
                d[i + 1] += carry;
            }
            sb.insert(0, mod);
        }

        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /* 44 */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i++)
            dp[i][0] = false;
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 1];
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /* 45 */
    public int jump(int[] nums) {
        int maxCover = 0;
        int lastCover = 0;
        int res = 0;
        for (int i = 0; i <= maxCover && i < nums.length; i++) {
            if (i > lastCover) {
                res++;
                lastCover = maxCover;
            }
            if (nums[i] + i > maxCover)
                maxCover = nums[i] + i;
        }
        if (maxCover < nums.length - 1)
            return 0;
        else
            return res;
    }
    /* 46 */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        helper46(nums, 0, res);
        return res;
    }
    public void helper46(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(convert(nums));
        }
        for (int i = index; i < nums.length; i ++) {
            swap(nums, index, i);
            helper46(nums, index + 1, res);
            swap(nums, index, i);
        }
    }
    public List<Integer> convert(int[] nums) {
        List<Integer> res = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i ++)
            res.add(nums[i]);
        return res;
    }
    /* 47 */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        helper47(nums, 0, res);
        return res;
    }
    public void helper47(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(convert(nums));
        }
        for (int i = index; i < nums.length; i ++) {
            if (checkDuplicate(nums, index, i)) {
                swap(nums, index, i);
                helper47(nums, index + 1, res);
                swap(nums, index, i);
            }
        }
    }
    public boolean checkDuplicate(int[] nums, int start, int end) {
        for (int i = start; i < end; i ++)
            if (nums[i] == nums[end])
                return false;
        return true;
    }
    /* 48 */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
    /* 49 */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<List<String>>();
        if (strs == null || strs.length == 0)
            return res;
        Arrays.sort(strs);
        HashMap<String, List<String>> hash = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i ++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String str = String.valueOf(arr);
            if (hash.containsKey(str)) {
                List<String> list = hash.get(str);
                list.add(strs[i]);
                hash.put(str, list);
            } else {
                List<String> list = new LinkedList<String>();
                list.add(strs[i]);
                hash.put(str, list);
            }
        }
        for (List<String> list: hash.values())
            res.add(list);
        return res;
    }
}