package Graph;

import java.util.*;

public class GraphProcessor {
    private Graph graph;

    public GraphProcessor(Graph graph) {
        this.graph = graph;
    }

    public Graph copyGraph() {
        return graph.copy();
    }

    // Метод для нахождения и удаления L4
    public static void findAndRemoveL4(Graph GPrime, Graph M) {
        boolean found = true;
        while (found) {
            found = false;
            List<Integer> vertices = new ArrayList<>(GPrime.getVertices());

            // Перебираем все возможные 4 вершины
            for (int v1 : vertices) {
                for (int v2 : GPrime.getNeighbors(v1)) {
                    for (int v3 : GPrime.getNeighbors(v2)) {
                        if (v3 == v1) continue; // избегаем петель
                        for (int v4 : GPrime.getNeighbors(v3)) {
                            if (v4 == v2 || v4 == v1) continue; // избегаем повторов

                            // Проверяем, есть ли замыкание v4-v1 (чтобы был цикл v1-v2-v3-v4-v1)
                            if (!GPrime.getNeighbors(v4).contains(v1)) continue;

                            // Теперь проверяем, сколько диагоналей есть (должна быть ровно одна)
                            boolean hasDiagonal13 = GPrime.getNeighbors(v1).contains(v3);
                            boolean hasDiagonal24 = GPrime.getNeighbors(v2).contains(v4);
                            int diagonalCount = (hasDiagonal13 ? 1 : 0) + (hasDiagonal24 ? 1 : 0);

                            if (diagonalCount == 2) {
                                M.addEdge(v1, v2);
                                M.addEdge(v2, v3);
                                M.addEdge(v3, v4);
                                M.addEdge(v4, v1);
                                M.addEdge(v1, v3);
                                M.addEdge(v2, v4);

                                // Удаляем вершины из GPrime
                                Set<Integer> toRemove = new HashSet<>(Arrays.asList(v1, v2, v3, v4));
                                GPrime.removeIncidentEdges(toRemove);
                                found = true;
                                break;
                            }

                            // Если диагональ ровно одна — это Q4
                            if (diagonalCount == 1) {
                                // Добавляем все рёбра цикла + диагональ в M
                                M.addEdge(v1, v2);
                                M.addEdge(v2, v3);
                                M.addEdge(v3, v4);
                                M.addEdge(v4, v1);
                                if (hasDiagonal13) {
                                    M.addEdge(v1, v3);
                                }
                                else {
                                    M.addEdge(v2, v4);
                                }

                                // Удаляем вершины из GPrime
                                Set<Integer> toRemove = new HashSet<>(Arrays.asList(v1, v2, v3, v4));
                                GPrime.removeIncidentEdges(toRemove);
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (found) break;
                }
                if (found) break;
            }
        }
    }
    public static void findAndEditL4(Graph GPrime, Graph M) {
        boolean found = true;
        while (found) {
            found = false;
            List<Integer> vertices = new ArrayList<>(GPrime.getVertices());

            // Перебираем все возможные 4 вершины
            for (int v1 : vertices) {
                for (int v2 : GPrime.getNeighbors(v1)) {
                    for (int v3 : GPrime.getNeighbors(v2)) {
                        if (v3 == v1) continue; // избегаем петель
                        for (int v4 : GPrime.getNeighbors(v3)) {
                            if (v4 == v2 || v4 == v1) continue; // избегаем повторов

                            // Проверяем, есть ли замыкание v4-v1 (чтобы был цикл v1-v2-v3-v4-v1)
                            if (!GPrime.getNeighbors(v4).contains(v1)) continue;

                            // Теперь проверяем, сколько диагоналей есть (должна быть ровно одна)
                            boolean hasDiagonal13 = GPrime.getNeighbors(v1).contains(v3);
                            boolean hasDiagonal24 = GPrime.getNeighbors(v2).contains(v4);
                            int diagonalCount = (hasDiagonal13 ? 1 : 0) + (hasDiagonal24 ? 1 : 0);

                            if (diagonalCount == 2) {
                                M.addEdge(v1, v2);
                                M.addEdge(v2, v3);
                                M.addEdge(v3, v4);
                                M.addEdge(v4, v1);
                                M.addEdge(v1, v3);
                                M.addEdge(v2, v4);

                                // Удаляем вершины из GPrime
                                Set<Integer> toRemove = new HashSet<>(Arrays.asList(v1, v2, v3, v4));
                                GPrime.removeIncidentEdges(toRemove);
                                found = true;
                                break;
                            }

                            // Если диагональ ровно одна — это Q4
                            if (diagonalCount == 1) {
                                // Добавляем все рёбра цикла + диагональ в M
                                M.addEdge(v1, v2);
                                M.addEdge(v2, v3);
                                M.addEdge(v3, v4);
                                M.addEdge(v4, v1);
                                M.addEdge(v1, v3);
                                M.addEdge(v2, v4);
                                GPrime.setEditingEdges(1);

                                // Удаляем вершины из GPrime
                                Set<Integer> toRemove = new HashSet<>(Arrays.asList(v1, v2, v3, v4));
                                GPrime.removeIncidentEdges(toRemove);
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (found) break;
                }
                if (found) break;
            }
        }
    }

    // Методы для нахождения и удаления K4, K3, K2
    public static void findAndRemoveK4(Graph GPrime, Graph M) {
        boolean found = true;
        while (found) {
            found = false;
            for (int vertex : GPrime.getVertices()) {
                List<Integer> neighbors = new ArrayList<>(GPrime.getNeighbors(vertex));
                if (neighbors.size() >= 3) {
                    for (int i = 0; i < neighbors.size(); i++) {
                        for (int j = i + 1; j < neighbors.size(); j++) {
                            for (int k = j + 1; k < neighbors.size(); k++) {
                                int v1 = neighbors.get(i);
                                int v2 = neighbors.get(j);
                                int v3 = neighbors.get(k);
                                if (GPrime.getNeighbors(v1).contains(v2) &&
                                        GPrime.getNeighbors(v2).contains(v3) &&
                                        GPrime.getNeighbors(v3).contains(v1)) {
                                    M.addEdge(vertex, v1);
                                    M.addEdge(vertex, v2);
                                    M.addEdge(vertex, v3);
                                    M.addEdge(v1, v2);
                                    M.addEdge(v2, v3);
                                    M.addEdge(v3, v1);

                                    Set<Integer> toRemove = new HashSet<>(Arrays.asList(vertex, v1, v2, v3));
                                    GPrime.removeIncidentEdges(toRemove);
                                    found = true;
                                    break;
                                }
                            }
                            if (found) break;
                        }
                        if (found) break;
                    }
                }
                if (found) break;
            }
        }
    }

    public static void findAndRemoveK3(Graph GPrime, Graph M) {
        boolean found = true;
        while (found) {
            found = false;
            for (int vertex : GPrime.getVertices()) {
                List<Integer> neighbors = new ArrayList<>(GPrime.getNeighbors(vertex));
                if (neighbors.size() >= 2) {
                    for (int i = 0; i < neighbors.size(); i++) {
                        for (int j = i + 1; j < neighbors.size(); j++) {
                            int v1 = neighbors.get(i);
                            int v2 = neighbors.get(j);
                            if (GPrime.getNeighbors(v1).contains(v2)) {
                                M.addEdge(vertex, v1);
                                M.addEdge(vertex, v2);
                                M.addEdge(v1, v2);

                                Set<Integer> toRemove = new HashSet<>(Arrays.asList(vertex, v1, v2));
                                GPrime.removeIncidentEdges(toRemove);
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                }
                if (found) break;
            }
        }
    }

    public static void findAndRemoveK2(Graph GPrime, Graph M) {
        boolean found = true;
        while (found) {
            found = false;
            for (int vertex : GPrime.getVertices()) {
                List<Integer> neighbors = new ArrayList<>(GPrime.getNeighbors(vertex));
                if (!neighbors.isEmpty()) {
                    int neighbor = neighbors.get(0);
                    M.addEdge(vertex, neighbor);
                    Set<Integer> toRemove = new HashSet<>(Arrays.asList(vertex, neighbor));
                    GPrime.removeIncidentEdges(toRemove);
                    found = true;
                    break;
                }
            }
        }
    }
}