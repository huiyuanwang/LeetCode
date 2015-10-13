import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by why on 10/8/15.
 */
public class Solution26 {
    /* 268 */
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i <= nums.length; i ++) {
            res ^= i;
        }
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }


}
