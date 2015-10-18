import java.util.HashMap;

/**
 * Created by why on 10/17/15.
 */
public class Solution24 {
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
