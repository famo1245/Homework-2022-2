public class Calculator {
    private State curState;
    private StateStart stateStart;
    private StateN1 stateN1;
    private StateOperator stateOperator;
    private StateN2 stateN2;
    private int n1;
    private char operator;
    private int n2;

    public Calculator() {
        stateStart = new StateStart(this);
        stateN1 = new StateN1(this);
        stateOperator = new StateOperator(this);
        stateN2 = new StateN2(this);
        initialize();
    }

    public void initialize() {
        curState = stateStart;
        n1 = 0;
        n2 = 0;
    }

    public void execute(char ch) {
        if(ch >= '0' && ch <= '9')
            curState.processDigit(ch - '0');
        else if (ch == '=')
            curState.processEqualOperator();
        else if (ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '%')
            curState.processArithmeticOperator(ch);
    }

    public void setStateStart() {
        curState = stateStart;
    }

    public void setStateN1() {
        curState = stateN1;
    }

    public void setStateOperator() {
        curState = stateOperator;
    }

    public void setStateN2() {
        curState = stateN2;
    }
    public void updateN1(int num) {
        n1 *= 10;
        n1 += num;
    }

    public void updateN2(int num) {
        n2 *= 10;
        n2 += num;
    }

    public void setOperator(char ch) {
        this.operator = ch;
    }

    public char getOperator() {
        return this.operator;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }
}
