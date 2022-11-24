package mp08;

import java.awt.*;
import java.awt.event.ActionListener;

public class OperatorButtonCommand extends CommandButton implements Command {
    private Calculator calculator;

    public OperatorButtonCommand(String text, ActionListener listener, Dimension buttonDimension,
                                 Calculator calculator) {
        super(text, listener, buttonDimension);
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        if (calculator.isOperand1Set()) {  // 첫 번째 피연산자 값이 지정되어야만 연산자 처리 가능
            calculator.setOperatorSet(true);
            calculator.setOperator(getText().charAt(0));
        }
    }
}
