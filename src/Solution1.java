import java.util.*;

/**
 * Created by why on 9/1/15.
 */
public class Solution1 {
    /* 10 */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i ++)
            dp[i][0] = false;
        for (int j = 1; j <= p.length(); j ++) {
            if (j - 2 >= 0 && p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }

        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 1; j <= p.length(); j ++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (j > 1 && dp[i][j - 2])
                        dp[i][j] = true;
                    else if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
    /* 11 */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, area);
            if (height[left] <height[right])
                left ++;
            else
                right --;
        }
        return max;
    }
    /* 12 */
    public String intToRoman(int num) {
        int[] ints = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length; i ++) {
            while (num >= ints[i]) {
                num -= ints[i];
                sb.append(romans[i]);
            }
        }
        return sb.toString();
    }
    /* 13 */
    public int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i --) {
            char ch = s.charAt(i);
            switch (ch) {
                case 'I':
                    if (res >= 5)
                        res -= 1;
                    else
                        res += 1;
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    if (res >= 50)
                        res -= 10;
                    else
                        res += 10;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    if (res >= 500)
                        res -= 100;
                    else
                        res += 100;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }
    /* 14 */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        StringBuilder sb = new StringBuilder();
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i ++)
            minLen = Math.min(minLen, strs[i].length());
        for (int i = 0; i < minLen; i ++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++)
                if (strs[j].charAt(i) != ch)
                    return sb.toString();
            sb.append(ch);
        }
        return sb.toString();
    }
    /* 15 */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> set = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return set;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i ++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int target = 0 - nums[i];
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    if (nums[low] + nums[high] == target) {
                        List<Integer> solution = new ArrayList<Integer>();
                        solution.add(nums[i]);
                        solution.add(nums[low]);
                        solution.add(nums[high]);
                        set.add(solution);
                        while (low < high && nums[low] == nums[low + 1])
                            low ++;
                        while (low < high && nums[high - 1] == nums[high])
                            high --;
                        low ++;
                        high --;
                    } else if (nums[low] + nums[high] > target)
                        high --;
                    else
                        low ++;
                }
            }
        }
        return set;
    }
    /* 16 */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        int min = Integer.MAX_VALUE;
        int close = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i ++) {
            int low = i + 1;
            int high = nums.length - 1;
            while(low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (Math.abs(target - sum) < min) {
                    min = Math.abs(target - sum);
                    close = sum;
                }
                if (sum == target)
                    return target;
                else if (sum > target)
                    high --;
                else
                    low ++;
            }
        }
        return close;
    }
    /* 17 */
    public List<String> letterCombinations(String digits) {
        String[] strs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        LinkedList<String> set = new LinkedList<String>();
        set.add("");
        for (int i = 0; i < digits.length(); i ++) {
            int index = digits.charAt(i) - '2';
            int size = set.size();
            for (int j = 0; j < size; j ++) {
                String temp = set.pop();
                for (int k = 0; k < strs[index].length(); k ++) {
                    set.add(temp + strs[index].charAt(k));
                }
            }
        }
        List<String> res = new LinkedList<String>();
        res.addAll(set);
        if (res.size() == 1)
            res.clear();
        return res;
    }
    /* 17(2) */
    public ArrayList<String> letterCombinations2(String digits) {
        String[] dic = {" ","", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ArrayList<String> ret = new ArrayList<String>();
        StringBuilder temp = new StringBuilder();
        dfs(digits, dic, 0, temp, ret);
        return ret;
    }
    public void dfs(String digits, String[] dic, int deep, StringBuilder temp,
                    ArrayList<String> ret){
        if(deep == digits.length()){
            ret.add(temp.toString());
            return;
        }
        else{
            for(int i = 0; i < dic[digits.charAt(deep) - '0'].length(); i++){
                temp.append(dic[digits.charAt(deep) - '0'].charAt(i));
                dfs(digits, dic, deep+1, temp, ret);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
    /* 18 */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> set = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4)
            return set;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i ++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j ++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int sum = target - nums[i] - nums[j];
                        int low = j + 1;
                        int high = nums.length - 1;
                        while (low < high) {
                            if (nums[low] + nums[high] == sum) {
                                List<Integer> solution = new ArrayList<Integer>();
                                solution.add(nums[i]);
                                solution.add(nums[j]);
                                solution.add(nums[low]);
                                solution.add(nums[high]);
                                set.add(solution);
                                while (low < high && nums[low] == nums[low + 1])
                                    low ++;
                                while (low < high && nums[high - 1] == nums[high])
                                    high --;
                                low ++;
                                high --;
                            } else if (nums[low] + nums[high] > sum)
                                high --;
                            else
                                low ++;
                        }
                    }
                }
            }
        }
        return set;
    }
    /* 19 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;
        ListNode p1 = head, p2 = head;
        for (int i = 0; i < n; i ++)
            p1 = p1.next;
        if (p1 == null)
            return head.next;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return head;
    }
}
