/**
 * Created by why on 9/10/15.
 */
public class Solution16 {
    /* 160 */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        ListNode tailA = null, tailB = null;
        while (true) {
            if (a == null) a = headB;
            if (b == null) b = headA;
            if (a.next == null) tailA = a;
            if (b.next == null) tailB = b;
            if (tailA != null && tailB != null && tailA != tailB) return null;
            if (a == b) return a;

            a = a.next;
            b = b.next;
        }
    }
    /* 162 */
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] > nums[1])
            return 0;
        if (nums[len - 2] < nums[len - 1])
            return len - 1;
        int low = 0, high = len - 1;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[mid + 1]) low = mid + 1;
            else if (nums[mid - 1] > nums[mid]) high = mid - 1;
            else return mid;
        }
        return nums[low] > nums[high] ? low : high;
    }
    /* 165 */
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");

        int i=0;
        while (i<arr1.length || i<arr2.length) {
            if (i<arr1.length && i<arr2.length) {
                if (Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])) {
                    return -1;
                } else if (Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])) {
                    return 1;
                }
            } else if (i < arr1.length) {
                if (Integer.parseInt(arr1[i]) != 0) {
                    return 1;
                }
            } else if (i < arr2.length) {
                if (Integer.parseInt(arr2[i]) != 0) {
                    return -1;
                }
            }
            i++;
        }
        return 0;
    }

    /* 168 */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n --;
            sb.append((char)('A' + n % 26));
            n /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
    /* 169 */
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] == res) {
                count ++;
            } else {
                if (count == 1) {
                    res = nums[i];
                } else {
                    count --;
                }
            }
        }
        return res;
    }
}
