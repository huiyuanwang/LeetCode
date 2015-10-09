/**
 * Created by why on 8/31/15.
 */
public class Main {
    public static void main(String[] args) {
        int[] input = {1, 0, 0, 1, 0, 0, 1, 0};

        Solution20 solution = new Solution20();
        Boolean res = solution.isHappy(2);
        //int res = flipWord(input);
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

}