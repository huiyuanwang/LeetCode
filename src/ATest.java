import java.util.*;

/**
 * Created by why on 10/15/15.
 */
public class ATest {
    public static List<List<String>> searchPalin (List<String> input) {
        List<List<String>> res = new LinkedList<>();
        if (input == null || input.size() == 0) return res;
        HashSet<String> set = new HashSet<>();
        for (String item: input) {
            set.add(item);
        }
        for (String str: input) {
            int len = str.length();
            String pre, suf;
            for (int i = 0; i < len; i ++) {
                pre = str.substring(0, i);
                suf = str.substring(i);
                String reverseSuf = reverse(suf);
                if (isPalindrome(pre) && set.contains(reverseSuf)) {
                    List<String> item = Arrays.asList(reverseSuf, str);
                    System.out.println(item);
                    res.add(item);
                }
                String reversePre = reverse(pre);
                if (isPalindrome(suf) && set.contains(reversePre)) {
                    List<String> item = new LinkedList<>();
                    item.add(str);
                    item.add(reversePre);
                    System.out.println(item);
                    res.add(item);
                }
            }
        }
        return res;
    }

    public static boolean isPalindrome(String str) {
        char[] arr = str.toCharArray();
        int low = 0, high = str.length() - 1;
        while (low < high) {
            if (arr[low ++] != arr[high --]) return false;
        }
        return true;
    }

    public static String reverse(String str) {
        char[] arr = str.toCharArray();
        int low = 0, high = str.length() - 1;
        while (low < high) {
            char temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low ++;
            high --;
        }
        return String.valueOf(arr);
    }


    public static List<String> parseCSV (String str) {
        List<String> res = new ArrayList<>();
        boolean inQuote = false;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < str.length(); i ++) {
            char ch = str.charAt(i);
            if (inQuote) {
                if (ch == '"') {
                    if (i != str.length() - 1 && str.charAt(i + 1) == '"') {
                        buf.append('"');
                        i ++;
                    } else {
                        inQuote = false;
                    }
                } else
                    buf.append(ch);
            } else {
                if (ch == '"') {
                    inQuote = true;
                } else if (ch == ',') {
                    res.add(buf.toString());
                    System.out.println(buf.toString());
                    buf.setLength(0);
                } else {
                    buf.append(ch);
                }
            }
        }
        if (buf.length() > 0)
            res.add(buf.toString());
        return res;
    }
}
