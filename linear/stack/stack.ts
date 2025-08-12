interface StackNode {
    value: number;
    nextStackNode: StackNode | null;
}

class Stack {
    private top: StackNode | null;

    constructor(firstStackNode: StackNode | null = null) {
        this.top = firstStackNode;
    }

    isEmpty(): boolean {
        return this.top == null;
    }

    peek(): StackNode | null {
        return this.top;
    }

    push(StackNode: StackNode): void {
        if (this.isEmpty()) {
            this.top = StackNode;
            return;
        }

        this.top
    }

    pop(): StackNode | null {

        if (this.isEmpty()) {
            console.log("The stack is empty");
            return null;
        }

        //@ts-ignore
        let temp: StackNode = this.top;
        //@ts-ignore
        this.top = this.top.nextStackNode;
        return temp;
    }

    emptyStack(): Array<number | null> {
        if (this.isEmpty()) {
            return new Array();
        }

        let arrayNumbers: Array<number> = new Array();
        let current: StackNode | null = this.top;

        while (current != null) {
            arrayNumbers.push(current.value);
            current = current.nextStackNode;
        }

        return arrayNumbers;
    }

    reverseStack(): void {
        let newStack: Stack = new Stack();
        
        while (!this.isEmpty()) {
            //@ts-ignore
            let element: StackNode = this.pop();     
            element.nextStackNode = null;
            newStack.push(element);
        }

        while (!newStack.isEmpty()) {
            //@ts-ignore
            let element: StackNode = newStack.pop();
            element.nextStackNode = null;
            this.push(element);
        }
    }   
}