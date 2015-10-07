import java.util.*;

/**
 * Created by why on 9/4/15.
 */
public class Solution3 {
    /* 30 */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<Integer>();
        HashMap<String, Integer> toFind = new HashMap<String, Integer>();
        int m = words.length, n = words[0].length();
        for (int i = 0; i < m; i ++){
            if (!toFind.containsKey(words[i]))
                toFind.put(words[i], 1);
            else
                toFind.put(words[i], toFind.get(words[i]) + 1);
        }
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        for (int i = 0; i <= s.length() - n * m; i ++){
            found.clear();
            int j;
            for (j = 0; j < m; j ++){
                int k = i + j * n;
                String stub = s.substring(k, k + n);
                if (!toFind.containsKey(stub))
                    break;
                if(!found.containsKey(stub))
                    found.put(stub, 1);
                else
                    found.put(stub, found.get(stub) + 1);
                if (found.get(stub) > toFind.get(stub))
                    break;
            }
            if (j == m)
                result.add(i);
        }
        return result;
    }
    /* 31 */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int aIndex = nums.length - 1;
        while (aIndex > 0) {
            if (nums[aIndex - 1] < nums[aIndex])
                break;
            aIndex --;
        }
        if (aIndex > 0) {
            aIndex --;
            int bIndex = nums.length - 1;
            while (bIndex > aIndex && nums[bIndex] <= nums[aIndex])
                bIndex --;
            swap(nums, aIndex, bIndex);
        }
        int end = nums.length - 1;
        aIndex ++;
        while (end > aIndex) {
            swap(nums, aIndex, end);
            aIndex ++;
            end --;
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }
    /* 32 */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int start = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty())
                        res = Math.max(res, i - start);
                    else
                        res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
    /* 33 */
    public int search(int[] nums, int target) {
        return helper33(nums, 0, nums.length - 1, target);
    }
    public int helper33(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target)
                return left;
            else
                return -1;
        }
        if (left + 1 == right) {
            if (nums[left] == target)
                return left;
            else if (nums[right] == target)
                return right;
            else
                return -1;
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid])
                    return binary33(nums, left, mid, target);
                else
                    return helper33(nums, mid + 1, right, target);
            } else {
                if (target >= nums[mid + 1] && target <= nums[right])
                    return binary33(nums, mid + 1, right, target);
                else
                    return helper33(nums, left, mid, target);
            }
        }
    }
    public int binary33(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target)
                return left;
            else
                return -1;
        }
        if (left + 1 == right) {
            if (nums[left] == target)
                return left;
            else if (nums[right] == target)
                return right;
            else
                return -1;
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                return binary33(nums, left, mid - 1, target);
            } else {
                return binary33(nums, mid + 1, right, target);
            }
        }
    }
    /* 34 */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        binary34(nums, 0, nums.length - 1, target, res);
        return res;
    }
    public void binary34(int[] nums, int left, int right, int target, int[] res) {
        if (left == right) {
            if (nums[left] == target) {
                res[0] = left;
                res[1] = left;
            }
        } else if (left + 1 == right) {
            if (nums[left] == target && nums[right] == target) {
                res[0] = left;
                res[1] = right;
            } else if (nums[left] == target) {
                res[0] = left;
                res[1] = left;
            } else if (nums[right] == target) {
                res[0] = right;
                res[1] = right;
            }
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                binary34(nums, left, mid - 1, target, res);
            } else if (nums[mid] < target) {
                binary34(nums, mid + 1, right, target, res);
            } else {
                res[0] = mid;
                res[1] = mid;
                int i = mid;
                while (i > left && nums[i] == nums[i - 1]) {
                    i --;
                    res[0] = i;
                }
                int j = mid;
                while (j < right && nums[j] == nums[j + 1]) {
                    j ++;
                    res[1] = j;
                }
            }
        }
    }
    /* 35 */
    public int searchInsert(int[] nums, int target) {
        return binary35(nums, 0, nums.length - 1, target);
    }
    public int binary35(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] >= target)
                return left;
            else
                return left + 1;
        }
        if (left + 1 == right) {
            if (nums[left] >= target)
                return left;
            else if (nums[right] >= target)
                return right;
            else
                return right + 1;
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                return binary35(nums, left, mid - 1, target);
            } else
                return binary35(nums, mid + 1, right, target);
        }
    }
    /* 36 */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> hash = new HashSet<Character>();
        /* row */
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == '.')
                    continue;
                if (hash.contains(board[i][j]))
                    return false;
                else
                    hash.add(board[i][j]);
            }
            hash.clear();
        }
        /* column */
        for (int j = 0; j < board[0].length; j ++) {
            for (int i = 0; i < board.length; i ++) {
                if (board[i][j] == '.')
                    continue;
                if (hash.contains(board[i][j]))
                    return false;
                else
                    hash.add(board[i][j]);
            }
            hash.clear();
        }
        /* block */
        for (int k = 0; k < 9; k ++) {
            for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i ++) {
                for (int j = k % 3 * 3; j < k % 3 * 3 + 3; j ++) {
                    if (board[i][j] == '.')
                        continue;
                    if (hash.contains(board[i][j]))
                        return false;
                    else
                        hash.add(board[i][j]);
                }
            }
            hash.clear();
        }
        return true;
    }
    /* 37 */
    public void solveSudoku(char[][] board) {
        trySolver(board);
    }
    public boolean trySolver(char[][] board) {
        for (int i = 0; i < board.length; i  ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == '.') {
                    for (char num = '1'; num <= '9'; num ++) {
                        if (isValid(board, i, j, num)) {
                            board[i][j] = num;
                            if (trySolver(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < board.length; i ++)
            if (board[i][col] == num)
                return false;
        for (int j = 0; j < board[0].length; j ++)
            if (board[row][j] == num)
                return false;
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i ++)
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j ++)
                if (board[i][j] == num)
                    return false;
        return true;
    }
    /* 38 */
    public String countAndSay(int n) {
        if (n <= 0)
            return "";
        String res = "1";
        int index = 1;
        while (index < n) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int i = 1; i < res.length(); i ++) {
                if (res.charAt(i) == res.charAt(i - 1))
                    count ++;
                else {
                    sb.append(count);
                    sb.append(res.charAt(i - 1));
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(res.charAt(res.length() - 1));
            res = sb.toString();
            index ++;
        }
        return res;
    }
    static String[] say_what_you_see(String[] input_strings) {
        if (input_strings == null) return null;
        String[] res = new String[input_strings.length];
        if (input_strings.length <= 0)
            return res;
        for(int i = 0; i < input_strings.length; i ++) {
            String item = input_strings[i];
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < item.length(); j ++) {
                if (item.charAt(j) == item.charAt(j - 1))
                    count ++;
                else {
                    sb.append(count);
                    sb.append(item.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(item.charAt(item.length() - 1));
            res[i] = sb.toString();
        }
        return res;
    }
    /* 39 */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper39(candidates, target, 0, item, res);
        return res;
    }
    public void helper39(int[] candidates, int target, int index, List<Integer> item,
                         List<List<Integer>> res) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new LinkedList<Integer>(item));
        } else {
            for (int i = index; i < candidates.length; i ++) {
                if (i == index || candidates[i] != candidates[i - 1]) {
                    int newTarget = target - candidates[i];
                    item.add(candidates[i]);
                    helper39(candidates, newTarget, index, item, res);
                    item.remove(item.size() - 1);
                }
            }
        }
    }
}
