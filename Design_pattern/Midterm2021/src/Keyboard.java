public class Keyboard extends AbstractExternalDeviceDecorator {
    public Keyboard(String type, AbstractNotebookComputer computer) {
        super(type, computer);
    }

    @Override
    public double requiredSpace() {
        return computer.requiredSpace() + 80;
    }
}
