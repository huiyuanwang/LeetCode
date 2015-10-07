/**
 * Created by why on 9/10/15.
 */
public class Solution16 {
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
}
