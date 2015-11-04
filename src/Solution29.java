import java.util.*;

/**
 * Created by why on 10/13/15.
 */
public class Solution29 {
    /* 290 */
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) return false;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        HashMap<String, Integer> record = new HashMap<String, Integer>();
        for (int i = 0; i < pattern.length(); i ++) {
            char ch = pattern.charAt(i);
            if (map.containsKey(ch)) {
                if (! record.containsKey(strs[i])) return false;
                else if (! map.get(ch).equals(record.get(strs[i]))) return false;
                else {
                    map.put(ch, i);
                    record.put(strs[i], i);
                }
            } else {
                if (record.containsKey(strs[i])) return false;
                else {
                    map.put(ch, i);
                    record.put(strs[i], i);
                }
            }
        }
        return true;
    }

    /* 292 */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public int minMergePoint(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    boolean[][] visited = new boolean[m][n];
                    BFShelper(distance, visited, i, j);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                res = Math.min(res, distance[i][j]);
            }
        }
        return res;
    }
    public void BFShelper(int[][] distance, boolean[][] visited, int i, int j) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int row = distance.length, col = distance[0].length;
        int start = i * col + j;
        queue.offer(start);
        int dis = 0;
        queue.offer(-1);
        while (! queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == -1) {
                if (! queue.isEmpty()) {
                    queue.offer(-1);
                    dis ++;
                }
                continue;
            }
            int m = cur / col, n = cur % col;
            distance[m][n] += dis;
            int newM = m - 1, newN = n;
            if (newM >= 0 && newM < row && newN >= 0 && newN < col && ! visited[newM][newN]) {
                visited[m][n] = true;
                queue.offer(newM * col + newN);
            }
            newM = m + 1;
            newN = n;
            if (newM >= 0 && newM < row && newN >= 0 && newN < col && ! visited[newM][newN]) {
                visited[m][n] = true;
                queue.offer(newM * col + newN);
            }
            newM = m;
            newN = n - 1;
            if (newM >= 0 && newM < row && newN >= 0 && newN < col && ! visited[newM][newN]) {
                visited[m][n] = true;
                queue.offer(newM * col + newN);
            }
            newM = m;
            newN = n + 1;
            if (newM >= 0 && newM < row && newN >= 0 && newN < col && ! visited[newM][newN]) {
                visited[m][n] = true;
                queue.offer(newM * col + newN);
            }
        }
    }
    /* 296 */
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> I = new ArrayList<>(m);
        List<Integer> J = new ArrayList<>(n);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    I.add(i);
                    J.add(j);
                }
            }
        }
        return getMin(I) + getMin(J);
    }
    private int getMin(List<Integer> list){
        int ret = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        while(i < j){
            ret += list.get(j--) - list.get(i++);
        }
        return ret;
    }
}
