import java.util.HashMap;

/**
 * Created by why on 9/8/15.
 */
public class Solution20 {
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
}
