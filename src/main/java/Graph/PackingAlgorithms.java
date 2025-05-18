package Graph;

public class PackingAlgorithms {
    private GraphProcessor graphProcessor;
    private int editingEdges = 0;

    public PackingAlgorithms(Graph graph) {
        this.graphProcessor = new GraphProcessor(graph);
    }

    public Graph q4ClusterEditingAlgorithm() {
        this.editingEdges = 0;
        Graph GPrime = graphProcessor.copyGraph();
        Graph M = new Graph(GPrime.getVertices().size());

        GraphProcessor.findAndRemoveK4(GPrime, M);
        GraphProcessor.findAndRemoveL4(GPrime, M);
        GraphProcessor.findAndRemoveK3(GPrime, M);
        GraphProcessor.findAndRemoveK2(GPrime, M);
        this.setEditingEdges(GPrime.getEditingEdges());

        return M;
    }
    public Graph l4ClusterEditingAlgorithm() {
        this.editingEdges = 0;
        Graph GPrime = graphProcessor.copyGraph();
        Graph M = new Graph(GPrime.getVertices().size());

        GraphProcessor.findAndEditL4(GPrime, M);
        GraphProcessor.findAndRemoveK3(GPrime, M);
        GraphProcessor.findAndRemoveK2(GPrime, M);
        this.setEditingEdges(GPrime.getEditingEdges());

        return M;
    }

    public Graph k4PackingAlgorithm() {
        this.editingEdges = 0;
        Graph GPrime = graphProcessor.copyGraph();
        Graph M = new Graph(GPrime.getVertices().size());

        GraphProcessor.findAndRemoveK4(GPrime, M);
        GraphProcessor.findAndRemoveK3(GPrime, M);
        GraphProcessor.findAndRemoveK2(GPrime, M);
        this.setEditingEdges(GPrime.getEditingEdges());

        return M;
    }

    public Graph q4PackingAlgorithm() {
        this.editingEdges = 0;
        Graph GPrime = graphProcessor.copyGraph();
        Graph M = new Graph(GPrime.getVertices().size());

        GraphProcessor.findAndRemoveK4(GPrime, M);
        GraphProcessor.findAndRemoveL4(GPrime, M);
        GraphProcessor.findAndRemoveK3(GPrime, M);
        GraphProcessor.findAndRemoveK2(GPrime, M);
        this.setEditingEdges(GPrime.getEditingEdges());

        return M;
    }

    public Graph l4PackingAlgorithm() {
        this.editingEdges = 0;
        Graph GPrime = graphProcessor.copyGraph();
        Graph M = new Graph(GPrime.getVertices().size());

        GraphProcessor.findAndRemoveL4(GPrime, M);
        GraphProcessor.findAndRemoveK3(GPrime, M);
        GraphProcessor.findAndRemoveK2(GPrime, M);
        this.setEditingEdges(GPrime.getEditingEdges());

        return M;
    }

    public int getEditingEdges() {
        return editingEdges;
    }

    public void setEditingEdges(int editingEdges) {
        this.editingEdges = editingEdges;
    }
}