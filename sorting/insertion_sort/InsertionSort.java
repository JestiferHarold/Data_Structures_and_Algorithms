public class Sorting {
    public static int[] InsertionSort(int[] unsortedArray) {
        int n = unsortedArray.length;
        int i = 0;
        int j, min, temp;

        while (i < n - 1) {
            min = i;
            j = i;
            
            while (j < n) {
                if (unsortedArray[j] < unsortedArray[min]) {
                    min = j;
                }
            }

            temp = unsortedArray[min];
            unsortedArray[min] = unsortedArray[i];
            unsortedArray[i] = temp;
        }

        return unsortedArray;
    }
}

class Test {
    
}