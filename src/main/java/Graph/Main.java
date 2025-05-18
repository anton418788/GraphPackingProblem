package Graph;

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

        for (int size : GRAPH_SIZES) {
            System.out.printf("\n=== Тестирование для графов с %d вершинами ===\n", size);
            double[] algo1Results = new double[NUM_TESTS];
            double[] algo2Results = new double[NUM_TESTS];
            double[] algo3Results = new double[NUM_TESTS];

            for (int i = 0; i < NUM_TESTS; i++) {
                // Генерация графа
                Graph randomGraph = Graph.generateErdosRenyiGraph(size, EDGE_PROBABILITY, random);
                //System.out.printf("\nГраф #%d:\n%s\n", i+1, randomGraph);

                GraphProcessor processor = new GraphProcessor(randomGraph);

                // Алгоритм 1
                Graph M1 = processor.algorithm1();
                //System.out.println("Результат Алгоритма 1:");
                //System.out.println(M1);
                //System.out.printf("Количество ребер: %d\n", countEdges(M1));

                // Алгоритм 2
                Graph M2 = processor.algorithm2();
                //System.out.println("Результат Алгоритма 2:");
                //System.out.println(M2);
                //System.out.printf("Количество ребер: %d\n", countEdges(M2));

                // Алгоритм 3
                Graph M3 = processor.algorithm3();
                //System.out.println("Результат Алгоритма 3:");
                //System.out.println(M3);
                //System.out.printf("Количество ребер: %d\n", countEdges(M3));

                algo1Results[i] = countEdges(M1);
                algo2Results[i] = countEdges(M2);
                algo3Results[i] = countEdges(M3);
            }

            // Итоговая статистика
            System.out.printf("\nИтог для %d вершин:\n", size);
            System.out.printf("Среднее Алгоритм 1: %.2f\n", Arrays.stream(algo1Results).average().orElse(0));
            System.out.printf("Среднее Алгоритм 2: %.2f\n", Arrays.stream(algo2Results).average().orElse(0));
            System.out.printf("Среднее Алгоритм 3: %.2f\n", Arrays.stream(algo3Results).average().orElse(0));
            System.out.println("-------------------");
        }
    }
}

