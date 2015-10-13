/**
 * Created by why on 8/31/15.
 */
public class Main {
    public static void main(String[] args) {
        int[] input = {1, 4, 4};

        //Solution21 solution = new Solution21();
        //int res = solution.minSubArrayLen(4, input);
        //int res = flipWord(input);

        long res = getLCM(Integer.MAX_VALUE, Integer.MAX_VALUE);
        System.out.println(res);

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