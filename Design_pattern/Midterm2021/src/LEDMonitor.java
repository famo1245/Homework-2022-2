public class LEDMonitor extends AbstractExternalDeviceDecorator {
    public LEDMonitor(String type, AbstractNotebookComputer computer) {
        super(type, computer);
    }

    @Override
    public double requiredSpace() {
        return computer.requiredSpace() + 150;
    }
}
