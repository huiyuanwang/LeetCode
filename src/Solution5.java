import java.util.*;

/**
 * Created by why on 9/6/15.
 */
public class Solution5 {
    /* 50 */
    public double myPow(double x, int n) {
        if (n >= 0)
            return power(x, n);
        else
            return 1 / power(x, -n);
    }
    public double power(double x, int n) {
        if (n == 0)
            return 1;
        double v = power(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
    /* 51 */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<List<String>>();
        if (n <= 0)
            return res;
        int[] record = new int[n];
        helper51(n, 0, record, res);
        return res;
    }
    public void helper51(int n, int index, int[] record, List<List<String>> res) {
        if (index == n) {
            List<String> solution = new LinkedList<String>();
            for (int i = 0; i < n; i ++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j ++) {
                    if (j == record[i])
                        sb.append('Q');
                    else
                        sb.append('.');
                }
                solution.add(sb.toString());
            }
            res.add(solution);
        } else {
            for (int i = 0; i < n; i ++) {
                record[index] = i;
                if (isValid(index, record)) {
                    helper51(n, index + 1, record, res);
                }
            }
        }
    }
    public boolean isValid(int index, int[] record) {
        for (int i = 0; i < index; i ++)
            if (record[index] == record[i] || Math.abs(record[index] - record[i]) == index - i)
                return false;
        return true;
    }
    /* 52 */
    public int totalNQueens(int n) {
        if (n <= 0)
            return 0;
        int[] record = new int[n];
        helper52(n, 0, record);
        return record[n];
    }
    public void helper52(int n, int index, int[] record) {
        if (n == index)
            record[n] ++;
        else {
            for (int i = 0; i < n; i ++) {
                record[index] = i;
                if (isValid(index, record))
                    helper52(n, index + 1, record);
            }
        }
    }
    /* 53 */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
    /* 54 */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        while(m > 0 && n > 0){
            if(m == 1){
                for(int i = 0; i < n; i ++){
                    res.add(matrix[x][y ++]);
                }
                break;
            }else if(n == 1){
                for(int i = 0; i < m; i ++){
                    res.add(matrix[x ++][y]);
                }
                break;
            }

            for(int i = 0; i < n - 1; i ++){
                res.add(matrix[x][y ++]);
            }
            for(int i = 0;i < m - 1; i ++){
                res.add(matrix[x ++][y]);
            }
            for(int i = 0;i < n - 1;i ++){
                res.add(matrix[x][y --]);
            }
            for(int i = 0;i < m - 1; i ++){
                res.add(matrix[x --][y]);
            }

            x ++;
            y ++;
            m -= 2;
            n -= 2;
        }
        return res;
    }
    /* 55 */
    public boolean canJump(int[] nums) {
        int maxCover = 0;
        for (int i = 0; i <= maxCover && i < nums.length; i ++) {
            if (nums[i] + i > maxCover)
                maxCover = nums[i] + i;
            if (maxCover > nums.length)
                return true;
        }
        return false;
    }
    /* 56 */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new LinkedList<Interval>();
        if (intervals == null || intervals.size() == 0)
            return res;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        Interval pre = intervals.get(0);
        for (int i = 1; i < intervals.size(); i ++) {
            if (intervals.get(i).start <= pre.end)
                pre.end = Math.max(pre.end, intervals.get(i).end);
            else {
                res.add(pre);
                pre = intervals.get(i);
            }
        }
        res.add(pre);
        return res;
    }
    /* 57 */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<Interval>();
        for (int i = 0; i < intervals.size(); i ++) {
            if (intervals.get(i).end < newInterval.start)
                res.add(intervals.get(i));
            else if (intervals.get(i).start > newInterval.end) {
                res.add(newInterval);
                newInterval = intervals.get(i);
            } else {
                if (intervals.get(i).start <= newInterval.end || newInterval.start <= intervals.get(i).end) {
                    newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
                    newInterval.end = Math.min(intervals.get(i).end, newInterval.end);
                }
            }
        }
        res.add(newInterval);
        return res;
    }
    /* 58 */
    public int lengthOfLastWord(String s) {
        int start = s.length() - 1;
        while (start >= 0 && s.charAt(start) == ' ')
            start --;
        int end = start;
        while (end >= 0 && s.charAt(end) != ' ')
            end --;
        if (end == 0 && s.charAt(0) != ' ')
            return (start - end + 1);
        else
            return (start - end);
    }
    /* 59 */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int k = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (left < right && top < bottom) {
            for (int j = left; j < right; j++) {
                res[top][j] = k++;
            }
            for (int i = top; i < bottom; i++) {
                res[i][right] = k++;
            }
            for (int j = right; j > left; j--) {
                res[bottom][j] = k++;
            }
            for (int i = bottom; i > top; i--) {
                res[i][left] = k++;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        if (n % 2 != 0)
            res[n / 2][n / 2] = k;
        return res;
    }
}
