import java.util.HashMap;

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
}
