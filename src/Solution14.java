import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by why on 9/24/15.
 */
public class Solution14 {
    /* 140 */
    public List<String> wordBreak(String s, Set<String> wordDict) {
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

}
