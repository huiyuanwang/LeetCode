import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by why on 10/7/15.
 */
public class Solution18 {
    /* 187 */

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<String>();
        if (s == null || s.length() < 10) return res;
        int[] dic = new int[20];
        dic[0] = 0;
        dic[2] = 1;
        dic[6] = 2;
        dic[19] = 3;
        int sub = 0;
        Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
        for (int i = 0; i < 10; i ++) {
            sub = sub << 2;
            sub |= dic[s.charAt(i) - 'A'];
        }
        hash.put(sub, 1);
        for (int i = 10; i < s.length(); i ++) {
            sub = sub << 2;
            sub |= dic[s.charAt(i) - 'A'];
            sub &= ~0x300000;
            if (hash.containsKey(sub)) {
                int num = hash.get(sub);
                num ++;
                if (num == 2) {
                    if (i != s.length() - 1) {
                        res.add(s.substring(i - 9, i + 1));
                    } else {
                        res.add(s.substring(i - 9));
                    }
                }
                hash.put(sub, num);
            }
            else {
                hash.put(sub, 1);
            }
        }
        return res;
    }
    /* 189 */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        if (k % nums.length == 0) return;
        k = k % nums.length;
        int i = 0, j = nums.length - k - 1;
        while (i < j) {
            swap(nums, i, j);
            i ++;
            j --;
        }
        i = nums.length - k;
        j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i ++;
            j --;
        }
        i = 0;
        j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i ++;
            j --;
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
