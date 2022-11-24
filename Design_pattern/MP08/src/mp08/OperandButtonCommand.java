package mp08;

import java.awt.*;
import java.awt.event.ActionListener;

public class OperandButtonCommand extends CommandButton implements Command {
    private CalcGUIV1 calcGUIV1;
    private Calculator calculator;

    public OperandButtonCommand(String text, ActionListener listener, Dimension buttonDimension,
                                CalcGUIV1 calc, Calculator calculator) {
        super(text, listener, buttonDimension);
        this.calcGUIV1 = calc;
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        if (calculator.isOperand1Set() && calculator.isOperatorSet()) { // 첫 번째 피연산자와 연산자가 지정되었다면 두 번째 피연산자 값으로 사용
            int num2 = Integer.parseInt(getText());
            calculator.setOperand2(num2);
            calcGUIV1.setText("" + num2);
            calculator.setOperand2Set(true);
        }
        else {  // 첫 번째 피연산자 값 지정
            int num1 = Integer.parseInt(getText());
            calcGUIV1.setText("" + num1);
            calculator.setOperand1(num1);
            calculator.setOperand1Set(true);
        }
    }
}
