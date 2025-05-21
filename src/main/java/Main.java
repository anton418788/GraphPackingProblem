import Experiment.Experiment;
import Graph.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Experiment.startExperiment(100, 0.25, new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        Experiment.startExperiment(100, 0.5, new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        Experiment.startExperiment(100, 0.75, new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
    }
}