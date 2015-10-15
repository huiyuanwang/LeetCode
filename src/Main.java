import java.util.*;

/**
 * Created by why on 8/31/15.
 */
public class Main {
    public static void main(String[] args) {
        int[][] array = {{1}, {}, {2, 3, 4}, {5, 6}, {}, {}, {7, 8, 9}, {}};
        ArrayIterator iter = new ArrayIterator(array);
        //iter.remove();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            if (iter.hasNext()) {
                iter.next();
                iter.remove();
                //iter.remove();
            }
        }
        List<String> output1 = ATest.parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
        System.out.println(output1);
        List<String> output2 = ATest.parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
        System.out.println(output2);

    }

    public static int flipWord(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int count = 0;
        for (int i: arr) {
            if (i == 1)
                count ++;
        }
        int max = 0;
        int total = 0;
        for (int element : arr) {
            total = Math.max(total, 0);
            if (element == 0) {
                total++;
            } else {
                total--;
            }
            max = Math.max(max, total);
        }
        return count + max;
    }

    public static long getLCM (int a, int b) {
        long product = (long) a * b;
        System.out.println(product);
        if (b > a) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println(a);
        return product / a;
    }

}