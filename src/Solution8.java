import java.util.*;

/**
 * Created by why on 9/4/15.
 */
public class Solution8 {
    /* 80 */
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length <= 1)
            return nums.length;
        int count = 0;
        int len = 0;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] != nums[i - 1]) {
                len ++;
                nums[len] = nums[i];
                count = 0;
            } else {
                if (count < 1) {
                    len ++;
                    nums[len] = nums[i];
                }
                count ++;
            }
        }
        return len + 1;
    }
    /* 81 */
    public boolean search(int[] nums, int target) {
        return helper81(nums, 0, nums.length - 1, target);
    }
    public boolean helper81(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target)
                return true;
            else
                return false;
        }
        if (left + 1 == right) {
            if (nums[left] == target || nums[right] == target)
                return true;
            else
                return false;
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid])
                    return binary81(nums, left, mid - 1, target);
                else
                    return helper81(nums, mid + 1, right, target);
            } else {
                if (target >= nums[mid + 1] && target <= nums[right])
                    return binary81(nums, mid + 1, right, target);
                else
                    return helper81(nums, left, mid - 1, target);
            }
        }
    }
    public boolean binary81(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target)
                return true;
            else
                return false;
        }
        if (left + 1 == right) {
            if (nums[left] == target || nums[right] == target)
                return true;
            else
                return false;
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[mid] > target) {
                return binary81(nums, left, mid - 1, target);
            } else {
                return binary81(nums, mid + 1, right, target);
            }
        }
    }
    /* 82 */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = head, cur = head, prev = dummy;
        boolean flag = false;
        while (p != null) {
            if (p.val == cur.val) {
                if (p != cur)
                    flag = true;
            } else {
                if (flag) {
                    prev.next = p;
                    flag = false;
                } else {
                    cur.next = p;
                    prev = cur;
                }
                cur = p;
            }
            p = p.next;
        }
        if (flag)
            prev.next = p;
        else
            cur.next = p;
        return dummy.next;
    }
    /* 83 */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy.next;
        ListNode cur = dummy.next;
        while (p != null) {
            if (p.val == cur.val)
                p = p.next;
            else {
                cur.next = p;
                cur = p;
                p = p.next;
            }
        }
        cur.next = p;
        return dummy.next;
    }
    /* 84 */
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i);
                i ++;
            }else {
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }



    /* 86 */
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode small = null, big = null;
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                if (small == null) {
                    small = p;
                    if (big != null) {
                        ListNode temp = p.next;
                        p.next = dummy.next;
                        dummy.next = p;
                        big.next = temp;
                        p = temp;
                    } else {
                        p = p.next;
                    }
                } else if (big != null) {
                    ListNode temp = p.next;
                    p.next = small.next;
                    small.next = p;
                    big.next = temp;
                    p = temp;
                    small = small.next;
                } else {
                    small = small.next;
                    p = p.next;
                }
            } else {
                if (big == null) {
                    big = p;
                } else {
                    big = big.next;
                }
                p = p.next;
            }
        }
        return dummy.next;
    }
    /* 88 */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[m + n - 1] = nums1[m - 1];
                m --;
            } else {
                nums1[m + n - 1] = nums2[n - 1];
                n --;
            }
        }
        while (n > 0) {
            nums1[n - 1] = nums2[n - 1];
            n --;
        }
    }
    /* 89 */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new LinkedList<Integer>();
        if (n == 0) return res;
        int[] num = new int[n];
        helper(n, 0, num, res);
        return res;
    }
    public void helper(int n, int index, int[] num, List<Integer> res) {
        if (index == n) {
            int sum = 0;
            for (int i = 0; i < n; i ++) {
                sum += num[i] * (1 << (n - i - 1));
            }
            res.add(sum);
            return;
        }
        int[] newNum = Arrays.copyOf(num, n);
        helper(n, index + 1, newNum, res);
        num[index] = 1;
        helper(n, index + 1, num, res);
    }
    public List<Integer> grayCode2(int n) {
        if(n == 0) {
            List<Integer> res = new ArrayList<Integer>();
            res.add(0);
            return res;
        }

        List<Integer> res = grayCode2(n-1);
        int addNumber = 1 << (n - 1);
        int len = res.size();

        for(int i = len - 1; i >= 0; i --) {
            res.add(addNumber + res.get(i));
        }
        return res;
    }

}
