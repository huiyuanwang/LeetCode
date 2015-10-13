import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by why on 10/8/15.
 */
public class Solution21 {
    /* 210 */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return null;
        int[] res = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i ++) {
                res[i] = i;
            }
            return res;
        }
        int[] record = new int[numCourses];
        int index = 0;
        for (int[] prerequisite : prerequisites) {
            record[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i ++) {
            if (record[i] == 0) {
                queue.offer(i);
                res[index] = i;
                index ++;
            }
        }
        while (! queue.isEmpty()) {
            int course = queue.poll();
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == course) {
                    record[prerequisite[0]]--;
                    if (record[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                        res[index] = prerequisite[0];
                        index ++;
                    }
                }
            }
        }
        if (index == numCourses) return res;
        else return new int[0];
    }

    /* 213 */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];

        /* Do not include the last element */
        int[] dl = new int[nums.length];
        dl[0] = 0;
        dl[1] = nums[0];
        for (int i = 1; i < nums.length - 1; i ++) {
            dl[i + 1] = Math.max(dl[i], dl[i - 1] + nums[i]);
        }

        /* Do not include the first element */
        int[] dh = new int[nums.length];
        dh[0] = 0;
        dh[1] = nums[1];
        for (int i = 2; i < nums.length; i ++) {
            dh[i] = Math.max(dh[i - 1], dh[i - 2] + nums[i]);
        }

        return Math.max(dl[nums.length - 1], dh[nums.length - 1]);
    }
}
