function bubbleSort(unsortedArray: number[]): number[] {
    let swapped: boolean;

    let length: number = unsortedArray.length;

    for (let i: number = 0; i < length; i++) {
        swapped = false;
        
        for (let j: number = 0; j < length - i - 1; j ++) {
            if (unsortedArray[j] > unsortedArray[j + 1]) {
                unsortedArray[j], unsortedArray[j + 1] = unsortedArray[j + 1], unsortedArray[j];
                swapped = true;
            }
        }

        if (!swapped) break; 
    }

    return unsortedArray;
}