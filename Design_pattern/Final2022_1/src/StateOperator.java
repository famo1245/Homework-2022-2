public class StateOperator implements State {
     private Calculator calc;

    public StateOperator(Calculator calc) {
        this.calc = calc;
    }

    @Override
    public void processDigit(int digit) {
        calc.updateN2(digit);
        calc.setStateN2();
    }
}
