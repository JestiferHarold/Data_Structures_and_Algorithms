package main

import "fmt"
	
func OptimizedBubbleSort(unsortedSlice []int) {
	var swapped bool

	var sliceLength int = len(unsortedSlice)

	for i := 0; i < sliceLength; i++ {
		swapped = false

		for j := 0; j < sliceLength-i-1; j++ {
			if unsortedSlice[j] > unsortedSlice[j+1] {
				unsortedSlice[j], unsortedSlice[j+1] = unsortedSlice[j+1], unsortedSlice[j]
				swapped = true
			}
		}

		if !swapped {
			break
		}
	}

}

func main() {
	fmt.Print()
}
