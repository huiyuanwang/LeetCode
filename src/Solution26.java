import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by why on 10/8/15.
 */
public class Solution26 {
    /* 260 */
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int sum = 0;
        for (int elem : nums)
            sum ^= elem;
        int mask = 0x1;
        while ((mask & sum) == 0)
            mask = mask << 1;
        for (int elem : nums) {
            if ((elem & mask) == 0)
                res[0] ^= elem;
            else
                res[1] ^= elem;
        }
        return res;
    }
    /* 263 */
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num != 1) {
            if (num % 2 == 0) num /= 2;
            else if (num % 3 == 0) num /= 3;
            else if (num % 5 == 0) num /= 5;
            else return false;
        }
        return true;
    }
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
