interface Node {
    value: number;
    nextnode: Node | null;
}

class Stack {
    private top: Node | null;

    constructor(firstNode: Node | null = null) {
        this.top = firstNode;
    }

    peek(): Node | null {
        return this.top;
    }
}