import Experiment.Experiment;
import Graph.*;

import java.util.*;

public class Main {
    private static final double EDGE_PROBABILITY = 0.25;


    public static void main(String[] args) {
        Random random = new Random(12345);
        Graph randomGraph2 = Graph.generateErdosRenyiGraph(20, EDGE_PROBABILITY, random);
        PackingAlgorithms algorithms6 = new PackingAlgorithms(randomGraph2);
        Graph M6 = algorithms6.k4PackingAlgorithm();
        System.out.println(Graph.countEdges(M6));
        System.out.println(Graph.countEdges(randomGraph2) - algorithms6.getEditingEdges());

        Experiment.startExperiment(100, 0.25, new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100});

//
    }
}