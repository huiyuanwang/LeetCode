import java.util.*;

/**
 * Created by why on 10/30/15.
 */
public class PhoneBook {
    public static void main(String[] args) {
        Set<String> dic = new HashSet<String>();
        dic.add("adg");
        dic.add("jmp");
        dic.add("jmq");
        String digits = "234567";
        List<String> res = wordBreak(digits, dic);
        System.out.println(res);

    }
    public static List<String> wordBreak (String s, Set<String> wordDict) {
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
                List<String> words = letterCombinations(cur);
                for (String word : words) {
                    if (wordDict.contains(word)) {
                        List<String> newList = new ArrayList<String>();
                        for (String str : dp[j]) {
                            if (str.equals(""))
                                newList.add(word);
                            else
                                newList.add(str + " " + word);
                        }
                        if (dp[i] == null) {
                            dp[i] = newList;
                        } else {
                            dp[i].addAll(newList);
                        }
                    }
                }
            }
        }
        return dp[len];
    }

    public static List<String> letterCombinations(String digits) {
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
