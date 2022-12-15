public class StateN1 implements State {
    private Calculator calc;

    public StateN1(Calculator calc) {
        this.calc = calc;
    }

    @Override
    public void processDigit(int digit) {
        calc.updateN1(digit);
    }

    @Override
    public void processArithmeticOperator(char ch) {
        calc.setOperator(ch);
        calc.setStateOperator();
    }
}
