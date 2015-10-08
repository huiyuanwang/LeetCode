import java.util.Stack;

/**
 * Created by why on 10/7/15.
 */
public class Solution15 {
    /* 150 */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num2 = stack.pop(), num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                }
            }
            else stack.push(Integer.parseInt(token));
        }
        return stack.pop();
    }
    /* 151 */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        String[] strs = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i --) {
            if (! strs[i].equals("")) res.append(strs[i]).append(" ");
        }
        return res.length() == 0 ? "" : res.substring(0, res.length() - 1);
    }
    /* 152 */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = nums[0], min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            int temp = max;
            max = Math.max(max * nums[i], min * nums[i]);
            max = Math.max(max, nums[i]);
            min = Math.min(temp * nums[i], min * nums[i]);
            min = Math.min(min, nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
    /* 153 */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return binarySearch(nums, 0, nums.length - 1);
    }
    public int binarySearch(int[] nums, int low, int high) {
        if (low + 1 < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[high]) {
                return binarySearch(nums, mid + 1, high);
            } else if (nums[mid] < nums[low]) {
                return binarySearch(nums, low, mid);
            }
        }
        return nums[low] < nums[high] ? nums[low] : nums[high];
    }
    /* 154 */
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return binarySearch2(nums, 0, nums.length - 1);
    }
    public int binarySearch2(int[] nums, int low, int high) {
        if (low + 1 < high) {
            if (nums[low] == nums[high]) {
                return binarySearch2(nums, low + 1, high);
            }
            int mid = (high - low) / 2 + low;
            if (nums[mid] > nums[high]) {
                return binarySearch2(nums, mid + 1, high);
            } else if (nums[mid] < nums[low]) {
                return binarySearch2(nums, low, mid);
            }
        }
        return nums[low] < nums[high] ? nums[low] : nums[high];
    }
}
