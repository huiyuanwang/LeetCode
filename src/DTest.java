import java.util.*;

/**
 * Created by why on 10/16/15.
 */
public class DTest {
    public List<String> letterCombinations(String digits) {
        String[] strs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        LinkedList<String> set = new LinkedList<String>();
        set.add("");
        for (int i = 0; i < digits.length(); i ++) {
            int index = digits.charAt(i) - '2';
            int size = set.size();
            for (int j = 0; j < size; j ++) {
                String temp = set.pop();
                for (int k = 0; k < strs[index].length(); k ++) {
                    set.add(temp + strs[index].charAt(k));
                }
            }
        }
        List<String> res = new LinkedList<String>();
        res.addAll(set);
        if (res.size() == 1)
            res.clear();
        return res;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper39(candidates, target, 0, item, res);
        return res;
    }
    public void helper39(int[] candidates, int target, int index, List<Integer> item,
                         List<List<Integer>> res) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new LinkedList<Integer>(item));
        } else {
            for (int i = index; i < candidates.length; i ++) {
                if (i == index || candidates[i] != candidates[i - 1]) {
                    int newTarget = target - candidates[i];
                    item.add(candidates[i]);
                    helper39(candidates, newTarget, i, item, res);
                    item.remove(item.size() - 1);
                }
            }
        }
    }

    public void gameOfLife (int[][] board) {
        if( board.length <= 0 || board[0].length <= 0) return;
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i ++) {
            for(int j = 0; j < col; j ++) {
                int x = getLiveNum(board, i, j);
                if(board[i][j] == 0) {
                    if(x == 3) board[i][j]+=10;
                }else {
                    if(x == 2 || x == 3) board[i][j]+=10;
                }
            }
        }
        for(int i = 0; i < row; i ++) {
            for(int j = 0; j < col; j ++) {
                if(board[i][j] / 10 ==1) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
    }
    public int getLiveNum(int[][] board, int x, int y) {
        int count = 0;
        for(int i = x - 1; i <= x + 1; i ++) {
            for(int j = y - 1; j <= y + 1; j ++) {
                if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || (i == x && j == y)) continue;
                if(board[i][j] %10 == 1) count++;
            }
        }
        return count;
    }

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

    public boolean wordBreak(String s, Set<String> wordDict) {
        int len = s.length();
        if (len == 0)
            return true;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i ++) {
            for (int j = 0; j <= i; j++) {
                if (!dp[j]) continue;
                if (wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
