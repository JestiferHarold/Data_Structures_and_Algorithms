import java.util.ArrayList;

public class DepthFirstSearch {
    public static void main(String[] args) {
        // Create a graph with 9 vertices (0 to 8).
        Graph graph = new Graph(9);

        // Add edges to the graph (directed edges).
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 8);

        // Perform DFS traversal of the graph.
        ArrayList<Integer> dfsResult = graph.dfsTraversal();

        // Print the result of DFS traversal.
        System.out.println("DFS Traversal of the graph: " + dfsResult);
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
     * Recursive function to perform Depth-First Search (DFS).
     *
     * @param currentNode       The current node being visited.
     * @param visited           Array to track visited nodes.
     * @param dfsTraversalOrder The list to store the order of DFS traversal.
     */
    private void performDFS(int currentNode, boolean[] visited, ArrayList<Integer> dfsTraversalOrder) {
        visited[currentNode] = true; // Mark the current node as visited
        dfsTraversalOrder.add(currentNode); // Add the current node to the traversal order

        // Recursively visit all unvisited neighbors of the current node
        for (Integer neighbor : adjacencyList.get(currentNode)) {
            if (!visited[neighbor]) {
                performDFS(neighbor, visited, dfsTraversalOrder);
            }
        }
    }

    /**
     * Performs DFS traversal of a graph from the starting node (node 0).
     *
     * @return A list of nodes in the order they are visited during DFS.
     */
    public ArrayList<Integer> dfsTraversal() {
        boolean[] visited = new boolean[numberOfVertices]; // To track visited nodes
        ArrayList<Integer> dfsTraversalOrder = new ArrayList<>(); // To store the traversal order

        // Start DFS from node 0
        performDFS(0, visited, dfsTraversalOrder);
        return dfsTraversalOrder;
    }
}
