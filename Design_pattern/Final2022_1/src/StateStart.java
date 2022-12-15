public class StateStart implements State {
    private Calculator calc;
    public StateStart(Calculator calc) {
        this.calc = calc;
    }

    @Override
    public void processDigit(int digit) {
        calc.initialize();
        calc.updateN1(digit);
        calc.setStateN1();
    }
}
