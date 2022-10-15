public abstract class AbstractExternalDeviceDecorator extends AbstractExternalDevice {
    AbstractNotebookComputer computer;

    public AbstractExternalDeviceDecorator(String type, AbstractNotebookComputer computer) {
        super(type);
        this.computer = computer;
    }

    @Override
    public String toString() {
        return computer.toString() + ", " + super.toString();
    }

    @Override
    public abstract double requiredSpace();
}
