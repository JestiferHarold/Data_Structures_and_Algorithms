package main

// ArraySize is the size of the hash table array
const ArraySize = 7

// hashTable represents the hash table with an array of buckets
type hashTable struct {
	array [ArraySize]*bucket
}

// bucket represents a linked list in each slot of the hash table
type bucket struct {
	head *bucketNode
}

// bucketNode represents a node in the linked list
type bucketNode struct {
	key  string
	next *bucketNode
}

// insertKey adds a key to the hash table
func (h *hashTable) insertKey(key string) bool {
	index := hash(key)
	return h.array[index].insert(key)
}

// searchKey returns true if the key exists in the hash table
func (h *hashTable) searchKey(key string) bool {
	index := hash(key)
	return h.array[index].search(key)
}

// deleteKey removes a key from the hash table
func (h *hashTable) deletekey(key string) bool {
	index := hash(key)
	return h.array[index].delete(key)
}

// insert adds a node with the given key to the bucket
func (b *bucket) insert(k string) bool {
	if !b.search(k) {
		newNode := &bucketNode{key: k}
		newNode.next = b.head
		b.head = newNode
		return true
	}
	return false
}

// search returns true if the key exists in the bucket
func (b *bucket) search(k string) bool {
	currentNode := b.head
	for currentNode != nil {
		if currentNode.key == k {
			return true
		}
		currentNode = currentNode.next
	}
	return false
}

// delete removes a node with the given key from the bucket
func (b *bucket) delete(k string) bool {
	if b.head == nil {
		return false
	}

	if b.head.key == k {
		b.head = b.head.next
		return true
	}

	previousNode := b.head
	for previousNode.next != nil {
		if previousNode.next.key == k {
			previousNode.next = previousNode.next.next
			return true
		}
		previousNode = previousNode.next
	}
	return false
}

// hash computes a hash value by summing ASCII values of characters and taking modulo ArraySize.
func hash(key string) int {
	sum := 0
	for _, v := range key {
		sum += int(v)
	}
	return sum % ArraySize
}

// Init will create a bucket in each slot of the hash table
func Init() *hashTable {
	result := &hashTable{}
	for i := range result.array {
		result.array[i] = &bucket{}
	}
	return result
}

func main() {
	HashTable := Init()
	list := []string{
		"ERIC", "KENNY", "KYLE", "STAN", "RANDY", "BUTTERS", "TOKEN",
	}

	for _, key := range list {
		HashTable.insertKey(key)
	}
}
