class Node {
    int value;
    Node nextNode;

    public Node(int value) {
        this.value = value;
        this.nextNode = null;
    }
}

public class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return this.head;
    }

    public int getSize() {
        int n = 0;

        if (this.head == null) {
            return n;
        }

        Node current = this.head;
        while (current != null) {
            n ++;
            current = current.nextNode;
        }

        return n;
    }
}

class Test {
    public static void main(String[] args) {
        SinglyLinkedList ll = new SinglyLinkedList();
        System.out.println(ll.getSize());
    }
}