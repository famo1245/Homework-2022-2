public class StateN2 implements State {
    private Calculator calc;

    public StateN2(Calculator calc) {
        this.calc = calc;
    }

    @Override
    public void processDigit(int digit) {
        calc.updateN2(digit);
    }

    @Override
    public void processEqualOperator() {
        switch (calc.getOperator()) {
            case '+':
                System.out.println(calc.getN1() + " " + calc.getOperator() + " " + calc.getN2() + " = " +
                        (calc.getN1() + calc.getN2()));
                break;
            case '-':
                System.out.println(calc.getN1() + " " + calc.getOperator() + " " + calc.getN2() + " = " +
                        (calc.getN1() - calc.getN2()));
                break;
            case '*':
                System.out.println(calc.getN1() + " " + calc.getOperator() + " " + calc.getN2() + " = " +
                        (calc.getN1() * calc.getN2()));
                break;
            case '/':
                System.out.println(calc.getN1() + " " + calc.getOperator() + " " + calc.getN2() + " = " +
                        (calc.getN1() / calc.getN2()));
                break;
            case '%':
                System.out.println(calc.getN1() + " " + calc.getOperator() + " " + calc.getN2() + " = " +
                        (calc.getN1() % calc.getN2()));
                break;
            default:
                break;

        }

        calc.setStateStart();
    }
}
