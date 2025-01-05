import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        // Create a Graph object with 6 vertices (0 to 5).
        Graph graph = new Graph(6);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        // Perform BFS starting from node 0.
        List<Integer> bfsTraversalResult = graph.performBFS(0);

        // Print the BFS Traversal.
        System.out.println("BFS Traversal of the graph:");
        for (int nodeValue : bfsTraversalResult) {
            System.out.print(nodeValue + " ");
        }
    }
}

class Graph {
    private ArrayList<ArrayList<Integer>> adjacencyList;
    private int numberOfVertices;

    /**
     * Constructor to create a graph with the specified number of vertices.
     *
     * @param numberOfVertices The number of vertices in the graph.
     */
    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    /**
     * Adds an edge to the adjacency list to represent a directed edge between two
     * vertices.
     *
     * @param from The source vertex of the edge.
     * @param to   The destination vertex of the edge.
     */
    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(to);
    }

    /**
     * Performs Breadth-First Search (BFS) on the graph.
     *
     * @param startingNode The node from which BFS traversal starts.
     * @return A list of node values in BFS traversal order.
     */
    public List<Integer> performBFS(int startingNode) {
        List<Integer> bfsOrder = new ArrayList<>();
        boolean[] visited = new boolean[numberOfVertices];
        Queue<Integer> nodesToVisit = new LinkedList<>();

        // Initialize BFS with the starting node.
        visited[startingNode] = true;
        nodesToVisit.add(startingNode);

        while (!nodesToVisit.isEmpty()) {
            int currentNode = nodesToVisit.poll();
            bfsOrder.add(currentNode);

            // Explore all neighbors of the current node.
            for (int neighbor : adjacencyList.get(currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    nodesToVisit.add(neighbor);
                }
            }
        }

        return bfsOrder;
    }
}
