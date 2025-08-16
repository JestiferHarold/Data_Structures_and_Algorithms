function InsertionSort(unsortedArray: number[]): number[] {
    let n: number = unsortedArray.length;

    for (let i = 0; i < n - 1; i ++) {
        let min: number = i;
        
        for (let j = i + 1; j < n; j ++) {
            if (unsortedArray[j] < unsortedArray[min]) {
                min = j;
            }
        }

        let temp: number = unsortedArray[i];
        unsortedArray[i] = unsortedArray[min];
        unsortedArray[min] = temp;
    }

    return unsortedArray;
}