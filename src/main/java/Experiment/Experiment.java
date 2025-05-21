package Experiment;

import Graph.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Experiment {


    public static void startExperiment (int NUM_TESTS, double EDGE_PROBABILITY, int[] GRAPH_SIZES) {
        Random random = new Random(123);
        System.out.printf("Экспериментальное исследование на случайных графах Эрдёша-Реньи с вероятностью возникновения ребра p = %.2f%n", EDGE_PROBABILITY);
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

                // Создание экземпляров PackingAlgorithms
                PackingAlgorithms algorithms1 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms2 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms3 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms4 = new PackingAlgorithms(randomGraph);
                PackingAlgorithms algorithms5 = new PackingAlgorithms(randomGraph);

                // Связь графов с алгоритмическим классом
                Graph M1 = algorithms1.k4PackingAlgorithm();
                Graph M2 = algorithms2.q4PackingAlgorithm();
                Graph M3 = algorithms3.l4PackingAlgorithm();
                Graph M4 = algorithms4.q4ClusterEditingAlgorithm();
                Graph M5 = algorithms5.l4ClusterEditingAlgorithm();

                // Определение кол-ва ребер в упаковке / отредактированных ребер
                algo1Results[i] = Graph.countEdges(randomGraph) - algorithms1.getEditingEdges();
                algo2Results[i] = Graph.countEdges(randomGraph) - algorithms2.getEditingEdges();
                algo3Results[i] = Graph.countEdges(randomGraph) - algorithms3.getEditingEdges();
                algo4Results[i] = algorithms4.getEditingEdges();
                algo5Results[i] = algorithms5.getEditingEdges();
            }

            // Итоговая статистика
            System.out.printf("\nИтог для %d вершин:\n", size);
            System.out.printf("Алгоритм k4P: %.2f ребер в среднем в упаковках\n", Arrays.stream(algo1Results).average().orElse(0));
            System.out.printf("Алгоритм q4P: %.2f ребер в среднем в упаковках\n", Arrays.stream(algo2Results).average().orElse(0));
            System.out.printf("Алгоритм l4P: %.2f ребер в среднем в упаковках\n", Arrays.stream(algo3Results).average().orElse(0));
            System.out.printf("Алгоритм q4C: %.2f ребер в среднем отредактировано\n", Arrays.stream(algo4Results).average().orElse(0));
            System.out.printf("Алгоритм l4C: %.2f ребер в среднем отредактировано\n", Arrays.stream(algo5Results).average().orElse(0));
            System.out.println("-------------------");
        }
    }

}
