import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by why on 9/8/15.
 */
public class Solution20 {
    /* 200 */
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j ++) {
                if (grid[i][j] == '1') {
                    count ++;
                    helper(grid, i, j);
                }
            }
        }
        return count;
    }
    public void helper(char[][] grid, int i, int j) {
        int m = grid.length - 1;
        int n = grid[0].length - 1;
        if (i < 0 || i > m || j < 0 || j > n) return;
        if (grid[i][j] == '0') return;
        grid[i][j] = '0';
        helper(grid, i - 1, j);
        helper(grid, i + 1, j);
        helper(grid, i, j - 1);
        helper(grid, i, j + 1);
    }
    /* 201 */
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n = n & (n - 1);
        }
        return n;
    }
    /* 202 */
    public boolean isHappy(int n) {
        int head = n, tail = n;
        int sum = n;
        while (sum != 1) {
            head = getSum(sum);
            if (head == 1) return true;
            tail = getSum(getSum(tail));
            if (head == tail) return false;
            sum = head;
        }
        return true;
    }
    public int getSum(int n) {
        int digit = n % 10;
        int residual = n / 10;
        int res = digit * digit;
        while (residual != 0) {
            digit = residual % 10;
            res += digit * digit;
            residual = residual / 10;
        }
        return res;
    }
    /* 203 */
    public ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode ptr = fakeHead;
        while (ptr.next != null) {
            ListNode cur = ptr.next;
            if (cur.val == val) {
                ptr.next = cur.next;
            } else {
                ptr = ptr.next;
            }
        }
        return fakeHead.next;
    }
    /* 204 */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
    /* 205 */
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> shash = new HashMap<Character, Integer>();
        HashMap<Character, Integer> thash = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i ++) {
            if (shash.containsKey(s.charAt(i))) {
                if (!thash.containsKey(t.charAt(i)))
                    return false;
                if (!shash.get(s.charAt(i)).equals(thash.get(t.charAt(i))))
                    return false;
            } else {
                if (thash.containsKey(t.charAt(i)))
                    return false;
            }
            shash.put(s.charAt(i), i);
            thash.put(t.charAt(i), i);
        }
        return true;
    }
    /* 206 */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode ptr = head;
        while (ptr.next != null) {
            ListNode next = ptr.next;
            ptr.next = next.next;
            next.next = fakeHead.next;
            fakeHead.next = next;
        }
        return fakeHead.next;
    }
    /* 207 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        int[] record = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            record[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i ++) {
            if (record[i] == 0) {
                queue.offer(i);
                System.out.println(i);
            }
        }
        int takeNum = queue.size();
        while (! queue.isEmpty()) {
            int course = queue.poll();
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == course) {
                    record[prerequisite[0]]--;
                    if (record[prerequisite[0]] == 0) {
                        takeNum++;
                        queue.offer(prerequisite[0]);
                        System.out.println(prerequisite[0]);
                    }
                }
            }
        }
        return takeNum == numCourses;
    }
    /* 209 */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0, end = 0;
        int res = Integer.MAX_VALUE;
        boolean found = false;
        int sum = nums[0];
        while (end < nums.length) {
            if (sum < s) {
                end ++;
                if (end < nums.length) {
                    sum += nums[end];
                }
            } else {
                found = true;
                sum -= nums[start];
                res = Math.min(res, end - start + 1);
                start ++;
            }
        }
        return found ? res : 0;
    }
}
