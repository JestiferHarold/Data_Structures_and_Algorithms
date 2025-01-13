/*
	Burst Sort (Radix Sort Variant)
	-------------------------------

	-> This algorithm uses a modified Trie Data Structure to sort strings.
	-> Each trie node has a bucket, in whcih the words are stored based on their prefix.
	-> Once the number of words in a bucket exceeds the threshold, it bursts.
	-> The trie node is then expanded with children and the words in the bucket are handled.
	-> The words are sorted (using radix sort) and stored in the respective children's bucket.
	-> At the end, the trie is traversed and the words from each bucket are collected.
	-> The words are now sorted alphabetically.

	The following code:
		1. Implements the modifies Trie Data Structure (Burst Trie).
		2. Implements the BurstSort algorithm (A Radix Sort Variant to sort within a bucket).
*/

package main

import (
	"fmt"
	"strings"
)

var Burst_Threshold = 3 // Default bucket holds a max of 3 words before it bursts.

type TrieNode struct {
	children map[rune]*TrieNode
	bucket   []string
	is_leaf  bool
	depth    int
}

type BurstTrie struct {
	root *TrieNode
}

// Creates a new TrieNode object and returns a pointer to it.
func CreateTrieNode(depth int) *TrieNode {
	return &TrieNode{
		children: make(map[rune]*TrieNode),
		bucket:   make([]string, 0),
		is_leaf:  false,
		depth:    depth,
	}
}

// Creates a new BurstTrie object and returns a pointer to it.
func CreateBurstTrie() *BurstTrie {
	return &BurstTrie{
		root: CreateTrieNode(0),
	}
}

// CountSort Helper Function for Radix Sort within the bucket.
func CountSort(words []string, index int) []string {
	char_frequency := make([]int, 256)
	sorted_words := make([]string, len(words))

	// Calculates frequency of characters.
	for _, word := range words {
		var char byte
		if index < len(word) {
			char = word[len(word)-index-1]
		} else {
			char = 0
		}
		char_frequency[char]++
	}

	// Calculates cumulative frequency.
	for i := 1; i < 256; i++ {
		char_frequency[i] += char_frequency[i-1]
	}

	// Builds the slice back up with the words sorted based on each index.
	for i := len(words) - 1; i >= 0; i-- {
		word := words[i]
		var char byte
		if index < len(word) {
			char = word[len(word)-index-1]
		} else {
			char = 0
		}
		sorted_words[char_frequency[char]-1] = word
		char_frequency[char]--
	}
	return sorted_words
}

// Radix Sort Algorithm to sort the words within the bucket.
func RadixSort(words []string) []string {
	max_length := 0
	for _, word := range words {
		if len(word) > max_length {
			max_length = len(word)
		}
	}
	for i := 0; i < max_length; i++ {
		words = CountSort(words, i) // Calls CountSort() for each char index.
	}
	return words
}

// Function that handles Bursting Phase.
func (current_node *TrieNode) Burst() {
	current_node.bucket = RadixSort(current_node.bucket) // Sorts the strings within the bucket.
	// Copies the strings to words before emptying bucket.
	words := current_node.bucket
	current_node.bucket = nil
	for _, word := range words {

		// This block executes if the word is already processed at the given depth.
		if len(word) <= current_node.depth {
			current_node.bucket = append(current_node.bucket, word) // Adds it back to the bucket.
			current_node.is_leaf = true
			continue
		}

		// This block executes to redistribute the word to child nodes.
		ch := rune(word[current_node.depth])
		if _, exists := current_node.children[ch]; !exists {
			current_node.children[ch] = CreateTrieNode(current_node.depth + 1)
		}
		child := current_node.children[ch]
		child.bucket = append(child.bucket, word)
		if len(child.bucket) > Burst_Threshold {
			child.Burst() // Recursively calls Burst() if the bucket bursts after redistribution.
		}
	}
}

// Function that handles the Insertion Phase.
func (trie *BurstTrie) Insert(word string) {
	word = strings.ToLower(word) // Normalise input to lowercase.
	current_node := trie.root
	for i, ch := range word {

		// This block executes when the node has words in a bucket but hasn't burst yet.
		if len(current_node.bucket) > 0 || len(current_node.children) == 0 {
			current_node.bucket = append(current_node.bucket, word)
			if len(current_node.bucket) > Burst_Threshold {
				current_node.Burst()
			}
			return
		}

		// This block executes when ch doesn't have a corresponding child node.
		if _, exists := current_node.children[ch]; !exists {
			new_node := CreateTrieNode(i + 1)
			new_node.bucket = append(new_node.bucket, word)
			current_node.children[ch] = new_node
			return
		}
		current_node = current_node.children[ch]
	}

	// This block executes when all chars have corresponding child nodes already.
	current_node.is_leaf = true
	current_node.bucket = append(current_node.bucket, word)
}

// Helper Fucntion that collects words from each node recursively in order.
func (trie *BurstTrie) CollectFromTrieNode(current_node *TrieNode, sorted_words *[]string) {
	if len(current_node.bucket) > 0 {
		current_node.bucket = RadixSort(current_node.bucket) // Sorts the bucket using Radix Sort before appending the words.
		*sorted_words = append(*sorted_words, current_node.bucket...)
		return
	}
	for ch := 0; ch < 256; ch++ {
		if child, exists := current_node.children[rune(ch)]; exists {
			trie.CollectFromTrieNode(child, sorted_words) // Recursively collects words from the next chld node one by one.
		}
	}
}

// Function that uses CollectFromTrieNode() to collect all words in sorted manner.
func (trie *BurstTrie) CollectWords() []string {
	var sorted_words []string
	trie.CollectFromTrieNode(trie.root, &sorted_words)
	return sorted_words
}

// BurstSort Function creates a BurstTrie, inserts teh words into the trie and collects the sorted words.
func BurstSort(words []string) []string {
	trie := CreateBurstTrie()
	for _, word := range words {
		trie.Insert(word)
	}
	return trie.CollectWords()
}

func main() {
	words := []string{"varun", "Midhunan2", "midhunan1", "narain", "aashiq", "aadith", "bash", "adithya", "#go", "batter", "anurup"}

	fmt.Println("Unsorted List:", words)
	sorted_words := BurstSort(words)
	fmt.Println("Sorted List:", sorted_words)
}
