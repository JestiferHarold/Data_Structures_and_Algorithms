interface Node {
    value: number;
    nextnode: Node | null;
}

class SinglyLinkedList {
    private head: Node | null;

    constructor(head: Node | null = null) {
        this.head = head;
    }

    getHead(): Node | null {
        return this.head;
    }

    getSize(): number {
        let n: number = 0;
        let current: Node | null = this.head
        
        while (current != null) {
            n ++;
            current = current.nextnode;
        }

        return n;
    }

    
}

export {
    Node,
    SinglyLinkedList
}