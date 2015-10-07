import java.util.*;

/**
 * Created by why on 8/31/15.
 */
public class Solution0 {
    /* 1 */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i ++) {
            int find = target - nums[i];
            if (hash.containsKey(find)) {
                res[0] = hash.get(find) + 1;
                res[1] = i + 1;
                return res;
            }
            else
                hash.put(nums[i], i);
        }
        return res;
    }
    /* 2 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p3 = head;
        int carry = 0;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }
            p3.next = new ListNode(carry % 10);
            carry /= 10;
            p3 = p3.next;
        }
        if (carry != 0) {
            p3.next = new ListNode(carry);
        }
        return head.next;
    }
    /* 3 */
    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue = new LinkedList<Character>();
        HashSet<Character> hash = new HashSet<Character>();
        int res = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (!hash.contains(s.charAt(i))) {
                hash.add(s.charAt(i));
                queue.add(s.charAt(i));
            }
            else {
                if (queue.size() > res)
                    res = queue.size();
                while(queue.peek() != s.charAt(i)) {
                    hash.remove(queue.poll());
                }
                queue.poll();
                queue.add(s.charAt(i));
            }
        }
        if (queue.size() > res)
            res = queue.size();
        return res;
    }
    /************HARD**************/
    /*BINARY SEARCH*/
    /* 4 */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if ((len1 + len2) % 2 == 1)
            return (double) findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2);
        else {
            int left = findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2 - 1);
            int right = findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2);
            return (left + right) * 0.5;
        }

    }
    public int findKth(int[] nums1, int start1, int end1,
                       int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        if (len1 == 0)
            return nums2[start2 + k];
        if (len2 == 0)
            return nums1[start1 + k];
        if (k == 0)
            return nums1[start1] < nums2[start2] ? nums1[start1] : nums2[start2];

        int med1 = len1 * k / (len1 + len2);
        int med2 = k - 1 - med1;

        med1 += start1;
        med2 += start2;

        if (nums1[med1] > nums2[med2]) {
            k -= (med2 - start2 + 1);
            end1 = med1;
            start2 = med2 + 1;
        } else {
            k -= (med1 - start1 + 1);
            end2 = med2;
            start1 = med1 + 1;
        }
        return findKth(nums1, start1,end1, nums2, start2, end2, k);
    }
    /* 5 */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return null;
        if (s.length() == 1)
            return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i ++) {
            String odd = fixedLongest(s, i, i);
            if (odd.length() > longest.length())
                longest = odd;
            String even = fixedLongest(s, i, i + 1);
            if (even.length() > longest.length())
                longest = even;
        }
        return longest;
    }
    public String fixedLongest(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start --;
            end ++;
        }
        return s.substring(start + 1, end);
    }
    /* 6 */
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int k = numRows - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i ++) {
            int j = i;
            int row = j;
            int co = k - row;
            while (j < s.length()) {
                sb.append(s.charAt(j));
                if (row == 0 || row == k) {
                    j += 2 * k;
                } else {
                    j += 2 * co;
                    co = k - co;
                }
            }
        }
        return sb.toString();
    }
    /* 7 */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int test = res * 10 + x % 10;
            if ((test - x % 10) / 10 != res)
                return 0;
            else
                res = test;
            x /= 10;
        }
        return res;
    }
    /* 8 */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        str = str.trim();
        char sign = '+';
        int index = 0;
        if (str.charAt(index) == '-') {
            sign = '-';
            index ++;
        } else if (str.charAt(index) == '+') {
            index ++;
        }
        double res = 0;
        while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
            res = res * 10 + str.charAt(index) - '0';
            index ++;
        }
        if (sign == '-') {
            res = 0 - res;
            if (res < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        } else {
            if (res > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
        }
        return (int)res;
    }
    /* 9 */
    public boolean isPalindrome(int x) {
        int y = 0, temp = x;
        while (temp != 0) {
            y = y * 10 + temp % 10;
            temp /= 10;
        }
        if (x < 0)
            y = 0 - y;
        if (x == y)
            return true;
        else
            return false;
    }
}
