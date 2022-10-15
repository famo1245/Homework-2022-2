public class NotebookComputer extends AbstractNotebookComputer {
    private String owner;

    public NotebookComputer(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return owner;
    }

    @Override
    public double requiredSpace() {
        return 250;
    }
}
