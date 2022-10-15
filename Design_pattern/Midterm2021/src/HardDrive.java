public class HardDrive extends AbstractExternalDeviceDecorator {
    public HardDrive(String type, AbstractNotebookComputer computer) {
        super(type, computer);
    }

    @Override
    public double requiredSpace() {
        return computer.requiredSpace() + 40;
    }
}
