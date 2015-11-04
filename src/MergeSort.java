import java.util.*;

public class MergeSort
{
    private int[] array;
    private int[] tempMergArr;
    private int length;

    public static void main(String a[]){

        int[] inputArr = {3, 1, 2, 5, 4};
        List<List<Integer>> res = new LinkedList<>();
        MergeSort ms = new MergeSort();
        ms.sort(inputArr, res);
        System.out.println(res);
        for(int i:inputArr){
            System.out.print(i);
            System.out.print(" ");
        }
    }

    public void sort(int inputArr[], List<List<Integer>> res) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1, res);
    }

    private void doMergeSort(int lowerIndex, int higherIndex, List<List<Integer>> res) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle, res);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex, res);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex, res);
        }
    }
    private void mergeParts(int lowerIndex, int middle, int higherIndex, List<List<Integer>> res) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                List<Integer> item = new LinkedList<>();
                item.add(tempMergArr[i]);
                item.add(tempMergArr[j]);
                res.add(item);
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
}