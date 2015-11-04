import java.util.*;

/**
 * Created by why on 10/16/15.
 */
public class DTest {
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
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }
    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        char c = pat.charAt(j);

        if (map.containsKey(c)) {
            String s = map.get(c);
            if (!str.startsWith(s, i)) {
                return false;
            }
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }
            map.put(c, p);
            set.add(p);
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }
            map.remove(c);
            set.remove(p);
        }
        return false;
    }

    public int count1 (int val[], int m, int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (m <= 0 && n >= 1) return 0;
        return count1(val, m - 1, n) + count1(val, m, n - val[m - 1]);
    }
    public int count2 (int val[], int m, int n) {
        int x, y;
        int[][] dp = new int[n + 1][m];

        for (int i = 1; i < n + 1; i ++) {
            for (int j = 0; j < m; j ++) {
                x = (i - val[j] >= 0) ? dp[i - val[j]][j] : 0;
                y = (j >= 1) ? dp[i][j - 1] : 0;
                dp[i][j] = x + y;
            }
        }
        return dp[n][m + 1];
    }
    public int count3 (int val[], int m, int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < m; i ++) {
            for (int j = val[i]; j <= n; j ++) {
                dp[j] += dp[j - val[i]];
            }
        }
        return dp[n];
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
    public List<String> wordBreak2 (String s, Set<String> wordDict) {
        int len = s.length();
        if (len == 0) return null;
        List<String> dp[] = new ArrayList[len + 1];
        List<String> list = new ArrayList<>();
        list.add("");
        dp[0] = list;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == null) {
                    continue;
                }
                String cur = s.substring(j, i);
                if (wordDict.contains(cur)) {
                    List<String> newList = new ArrayList<String>();
                    for (String str : dp[j]) {
                        if (str.equals(""))
                            newList.add(cur);
                        else
                            newList.add(str + " " + cur);
                    }
                    if (dp[i] == null) {
                        dp[i] = newList;
                    } else {
                        dp[i].addAll(newList);
                    }
                }
            }
        }
        return dp[len];
    }

    public List<String> wordBreak3(String s, Set<String> wordDict) {
        return helper(new ArrayList[s.length()], s, wordDict, s.length() - 1);
    }
    public List<String> helper(List[] dp, String s, Set<String> dict, int index) {
        if (dp[index] != null) return dp[index];
        List<String> list = new ArrayList<String>();
        for (int i = index; i >= 0; i --) {
            String test = s.substring(i, index + 1);
            if (dict.contains(test)) {
                if (i == 0)
                    list.add(test);
                else {
                    List<String> prev = helper(dp, s, dict, i - 1);
                    for (String item: prev) {
                        list.add(item + ' ' + test);
                    }
                }
            }
        }
        dp[index] = list;
        return list;
    }

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
}
