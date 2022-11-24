package mp08;

import java.awt.*;
import java.awt.event.ActionListener;

public class EqualButtonCommand extends CommandButton implements Command {
    private CalcGUIV1 calcGUIV1;
    private Calculator calculator;

    public EqualButtonCommand(String text, ActionListener listener, Dimension buttonDimension,
                              CalcGUIV1 calc, Calculator calculator) {
        super(text, listener, buttonDimension);
        this.calcGUIV1 = calc;
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        int result = 0;
        if (calculator.isOperand1Set() && calculator.isOperand2Set()
                && calculator.isOperatorSet()) { // 두 개 피 연산자값과 연산자가 지정되었다면
            if (calculator.getOperator() == '+') {
                result = calculator.getOperand1() + calculator.getOperand2();
            }
            else if (calculator.getOperator() == '-') {
                result = calculator.getOperand1() - calculator.getOperand2();
            }
            else if (calculator.getOperator() == '*') {
                result = calculator.getOperand1() * calculator.getOperand2();
            }
            else if (calculator.getOperator() == '/') {
                result = calculator.getOperand1() / calculator.getOperand2();
            }
        }
        calcGUIV1.setText("" + result);
        calculator.clearFlags();
    }
}
