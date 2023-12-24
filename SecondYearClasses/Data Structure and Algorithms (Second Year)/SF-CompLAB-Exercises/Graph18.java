

import java.util.*;

public class Graph18 {
    private static class Graph {
        private final Map<String, List<String>> adjacencyList;

        public Graph() {
            this.adjacencyList = new HashMap<>();
        }

        public void addVertex(String vertex) {
            adjacencyList.put(vertex, new ArrayList<>());
        }

        public void addEdge(String source, String destination) {
            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source); // Assuming an undirected graph
        }

        public List<String> getNeighbors(String vertex) {
            return adjacencyList.get(vertex);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = createGraph();

        boolean tryAgain = true;

        while (tryAgain) {
            System.out.print("Input node 1: ");
            String node1 = scanner.nextLine().toUpperCase();

            System.out.print("Input node 2: ");
            String node2 = scanner.nextLine().toUpperCase();

            if (!graph.adjacencyList.containsKey(node1) || !graph.adjacencyList.containsKey(node2)) {
                System.out.println("Invalid input nodes.");
                System.out.print("Do you want to try again? (1 for Yes, 2 for No): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                if (choice == 2) {
                    tryAgain = false;
                }
            } else {
                List<String> shortestPath = findShortestPath(graph, node1, node2);

                if (shortestPath != null) {
                    System.out.println("\nShortest path between " + node1 + " & " + node2 + "\n");
                    System.out.println(shortestPath);
                } else {
                    System.out.println("No path found between " + node1 + " & " + node2);
                }

                tryAgain = false;
            }
        }
    }

    private static Graph createGraph() {
        Graph graph = new Graph();

        // Construct the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("K");

        graph.addEdge("K", "B");
        graph.addEdge("B", "A");
        graph.addEdge("A", "C");
        graph.addEdge("C", "F");
        graph.addEdge("F", "H");
        graph.addEdge("D", "C");
        graph.addEdge("G", "F");
        graph.addEdge("E", "D");

        return graph;
    }

    private static List<String> findShortestPath(Graph graph, String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);

                    if (neighbor.equals(end)) {
                        return reconstructPath(parentMap, start, end);
                    }
                }
            }
        }

        return null; // No path found
    }

    private static List<String> reconstructPath(Map<String, String> parentMap, String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
