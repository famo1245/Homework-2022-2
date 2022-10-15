public abstract class AbstractExternalDevice extends AbstractNotebookComputer {
    private String deviceType;

    public AbstractExternalDevice(String type) {
        deviceType = type;
    }

    public String toString() {
        return deviceType;
    }
}
