public class Sorting {
    public static int[] BubbleSort(int[] unsortedArray) {
        boolean swapped;
        int len = unsortedArray.length();

        for (int i = 0; i < len; i ++) {
            swapped = false;

            for (int j = 0; j < len - i - 1; j ++) {
                if (unsortedArray[j] > unsortedArray[j + 1]) {
                    int temp = unsortedArray[j + 1];
                    unsortedArray[j + 1] = unsortedArray[j];
                    unsortedArray[j] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return unsortedArray;

    } 
}

class Test {
    public static void main(String[] args) {
        int[] array = {12, 31, 1, 41, 13};

        System.out.println(Sorting.BubbleSort(array));


    }
}