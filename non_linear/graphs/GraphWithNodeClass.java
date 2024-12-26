import java.util.*;
class GraphNode {
    int value; // Value of the node
    List<GraphNode> neighbors; // Neighbors of the node
    // Constructor to initialize a graph node
    public GraphNode(int value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }
    /**
     * Performs BFS starting from the current node.
     *
     * @return A list of node values in BFS order.
     */
    public List<Integer> bfsTraversal() {
        List<Integer> bfsResult = new ArrayList<>(); // To store the BFS order
        Set<GraphNode> visitedNodes = new HashSet<>(); // To track visited nodes
        Queue<GraphNode> queue = new LinkedList<>(); // Queue for BFS traversal

        queue.add(this); // Start with the current node
        visitedNodes.add(this);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();
            bfsResult.add(currentNode.value); // Add the node to the BFS result

            // Add unvisited neighbors to the queue
            for (GraphNode neighbor : currentNode.neighbors) {
                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return bfsResult;
    }
}

public class GraphWithNodeClass {
    public static void main(String[] args) {
        // Create graph nodes
        GraphNode node0 = new GraphNode(0);
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        GraphNode node5 = new GraphNode(5);

        // Define the edges (neighbors)
        node0.neighbors.add(node1);
        node0.neighbors.add(node2);
        node1.neighbors.add(node3);
        node1.neighbors.add(node4);
        node2.neighbors.add(node3);
        node3.neighbors.add(node4);
        node3.neighbors.add(node5);

        // Perform BFS starting from node0
        List<Integer> bfsResult = node0.bfsTraversal();

        // Print the BFS Traversal
        System.out.println("\nBFS Traversal of the graph:");
        for (int nodeValue : bfsResult) {
            System.out.print(nodeValue + " ");
        }
    }
}
