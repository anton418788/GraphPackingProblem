package Graph;

import java.util.*;

public class Graph {
    private int numVertices;
    private Map<Integer, Set<Integer>> adjacencyList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 1; i <= numVertices; i++) {
            adjacencyList.put(i, new HashSet<>());
        }
    }

    /**
     * Генерирует случайный граф Эрдёша-Реньи G(n, p)
     * @param n количество вершин
     * @param p вероятность существования ребра (0 ≤ p ≤ 1)
     * @param random объект Random для генерации случайных чисел
     * @return новый граф G(n, p)
     */
    public static Graph generateErdosRenyiGraph(int n, double p, Random random) {
        Graph graph = new Graph(n);
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (random.nextDouble() < p) {
                    graph.addEdge(i, j);
                }
            }
        }
        return graph;
    }

    public void addEdge(int v1, int v2) {
        if (v1 != v2) {
            adjacencyList.get(v1).add(v2);
            adjacencyList.get(v2).add(v1);
        }
    }

    public void removeEdge(int v1, int v2) {
        adjacencyList.get(v1).remove(v2);
        adjacencyList.get(v2).remove(v1);
    }

    public void removeIncidentEdges(Set<Integer> vertices) {
        for (int v : vertices) {
            for (int neighbor : new HashSet<>(adjacencyList.get(v))) {
                removeEdge(v, neighbor);
            }
        }
    }

    public Set<Integer> getNeighbors(int v) {
        return adjacencyList.get(v);
    }

    public Graph copy() {
        Graph newGraph = new Graph(numVertices);
        for (int v : adjacencyList.keySet()) {
            newGraph.adjacencyList.put(v, new HashSet<>(adjacencyList.get(v)));
        }
        return newGraph;
    }

    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    public String toString() {
        return adjacencyList.toString();
    }
}

