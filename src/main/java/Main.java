import Graph.*;

import java.util.*;

public class Main {
    private static final int NUM_TESTS = 100;
    private static final double EDGE_PROBABILITY = 0.75;
    private static final int[] GRAPH_SIZES = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

    private static int countEdges(Graph graph) {
        int edgeCount = 0;
        for (int v : graph.getVertices()) {
            edgeCount += graph.getNeighbors(v).size();
        }
        return edgeCount / 2;
    }

    public static void main(String[] args) {
        Random random = new Random(12345);
        Graph randomGraph2 = Graph.generateErdosRenyiGraph(20, EDGE_PROBABILITY, random);
        PackingAlgorithms algorithms6 = new PackingAlgorithms(randomGraph2);
        Graph M6 = algorithms6.k4PackingAlgorithm();
        System.out.println(String.valueOf(countEdges(M6)));
        System.out.println(String.valueOf(countEdges(randomGraph2) - algorithms6.getEditingEdges()));

        for (int size : GRAPH_SIZES) {
            System.out.printf("\n=== Тестирование для графов с %d вершинами ===\n", size);
            double[] algo1Results = new double[NUM_TESTS];
            double[] algo2Results = new double[NUM_TESTS];
            double[] algo3Results = new double[NUM_TESTS];
            double[] algo4Results = new double[NUM_TESTS];
            double[] algo5Results = new double[NUM_TESTS];

            for (int i = 0; i < NUM_TESTS; i++) {
                // Генерация графа
                Graph randomGraph = Graph.generateErdosRenyiGraph(size, EDGE_PROBABILITY, random);

                // Создаем экземпляр PackingAlgorithms
                PackingAlgorithms algorithms1 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms2 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms3 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms4 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms5 = new PackingAlgorithms(randomGraph);

                // Вызываем методы через экземпляр
                Graph M1 = algorithms1.k4PackingAlgorithm();
                Graph M2 = algorithms2.q4PackingAlgorithm();
                Graph M3 = algorithms3.l4PackingAlgorithm();
                Graph M4 = algorithms4.q4ClusterEditingAlgorithm();
                Graph M5 = algorithms5.l4ClusterEditingAlgorithm();


                algo1Results[i] = countEdges(randomGraph) - algorithms1.getEditingEdges();
                algo2Results[i] = countEdges(randomGraph) - algorithms2.getEditingEdges();
                algo3Results[i] = countEdges(randomGraph) - algorithms3.getEditingEdges();
                algo4Results[i] = countEdges(randomGraph) - algorithms4.getEditingEdges();
                algo5Results[i] = countEdges(randomGraph) - algorithms5.getEditingEdges();
            }

            // Итоговая статистика
            System.out.printf("\nИтог для %d вершин:\n", size);
            System.out.printf("Среднее Алгоритм k4P: %.2f\n", Arrays.stream(algo1Results).average().orElse(0));
            System.out.printf("Среднее Алгоритм q4P: %.2f\n", Arrays.stream(algo2Results).average().orElse(0));
            System.out.printf("Среднее Алгоритм l4P: %.2f\n", Arrays.stream(algo3Results).average().orElse(0));
            System.out.printf("Среднее Алгоритм q4C: %.2f\n", Arrays.stream(algo4Results).average().orElse(0));
            System.out.printf("Среднее Алгоритм l4C: %.2f\n", Arrays.stream(algo5Results).average().orElse(0));
            System.out.println("-------------------");
        }
    }
}