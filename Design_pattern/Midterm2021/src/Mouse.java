public class Mouse extends AbstractExternalDeviceDecorator {
    public Mouse(String type, AbstractNotebookComputer computer) {
        super(type, computer);
    }

    @Override
    public double requiredSpace() {
        return computer.requiredSpace() + 30;
    }
}
