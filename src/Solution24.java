import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by why on 10/17/15.
 */
public class Solution24 {
    /* 240 */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) i --;
            else j ++;
        }
        return false;
    }
    /* 241 */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        if (input == null || input.length() == 0) return res;
        for (int i = 0; i < input.length(); i ++) {
            char ch = input.charAt(i);
            if (ch != '+' && ch != '-' && ch != '*') continue;
            List<Integer> left = diffWaysToCompute(input.substring(0, i));
            List<Integer> right = diffWaysToCompute(input.substring(i + 1));
            for (Integer m : left) {
                for (Integer n : right) {
                    switch (ch) {
                        case '+':
                            res.add(m + n);
                            break;
                        case '-':
                            res.add(m - n);
                            break;
                        case '*':
                            res.add(m * n);
                            break;
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
    /* 242 */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char ch: s.toCharArray()) {
            if (map.containsKey(ch)) {
                int num = map.get(ch);
                num += 1;
                map.put(ch, num);
            } else {
                map.put(ch, 1);
            }
        }
        for (char ch: t.toCharArray()) {
            if (map.containsKey(ch)) {
                int num = map.get(ch);
                if (num == 1) {
                    map.remove(ch);
                } else {
                    num -= 1;
                    map.put(ch, num);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
