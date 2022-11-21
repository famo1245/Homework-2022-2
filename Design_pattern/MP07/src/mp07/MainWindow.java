package mp07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends FrameWindow implements ActionListener {
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 250;
    private static final int NUM_BUTTONS = 3;
    private static final int BUTTON_HEIGHT = 50;
    private static final String HUNDRED_BUTTON_TITLE = "100";
    private static final String THOUSAND_BUTTON_TITLE = "1000";
    private static final String FIVE_HUNDRED_BUTTON_TITLE = "500";
    private static final String RETURN_CHANGE_BUTTON_TITLE = "Return Changes";
    private static final String SELECT_BEVERAGE_BUTTON_TITLE = "Select a beverage";
    private JLabel balanceLabel;  // 현재 투입 금액을 나타내는 화면
    private JLabel msgLabel; // 기타 메시지를 보여주기 위한 화면
    private MyButton hundredButton;
    private MyButton fiveHundredButton;
    private MyButton thousandButton;
    private MyButton returnChangesButton;
    private MyButton selectBeverageButton;
    private int balance;
    private State curState;
    private State0 state0;
    private StateLess500 stateLess500;
    private State500 state500;
    private StateLess1000 stateLess1000;
    private StateEqualOrMore1000 stateEqualOrMore1000;

    public MainWindow(String title) {
        super();
        createWindow(title, X, Y, WIDTH, HEIGHT);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        state0 = new State0(this);
        stateLess500 = new StateLess500(this);
        state500 = new State500(this);
        stateLess1000 = new StateLess1000(this);
        stateEqualOrMore1000 = new StateEqualOrMore1000(this);
        curState = state0;
        balance = 0;
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        balanceLabel = new JLabel();
        msgLabel = new JLabel();

        // create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JPanel coinButtonPanel = new JPanel();
        coinButtonPanel.setLayout(new BoxLayout(coinButtonPanel, BoxLayout.X_AXIS));
        coinButtonPanel.setPreferredSize(new Dimension(width, BUTTON_HEIGHT));
        hundredButton = new MyButton(HUNDRED_BUTTON_TITLE);
        hundredButton.setProperties(this, width / NUM_BUTTONS, BUTTON_HEIGHT);
        coinButtonPanel.add(hundredButton);
        fiveHundredButton = new MyButton(FIVE_HUNDRED_BUTTON_TITLE);
        fiveHundredButton.setProperties(this, width / NUM_BUTTONS, BUTTON_HEIGHT);
        coinButtonPanel.add(fiveHundredButton);
        thousandButton = new MyButton(THOUSAND_BUTTON_TITLE);
        thousandButton.setProperties(this, width / NUM_BUTTONS, BUTTON_HEIGHT);
        coinButtonPanel.add(thousandButton);
        buttonPanel.add(coinButtonPanel, BorderLayout.NORTH);
        returnChangesButton = new MyButton(RETURN_CHANGE_BUTTON_TITLE);
        returnChangesButton.setProperties(this, width / NUM_BUTTONS, BUTTON_HEIGHT);
        buttonPanel.add(returnChangesButton, BorderLayout.CENTER);
        selectBeverageButton = new MyButton(SELECT_BEVERAGE_BUTTON_TITLE);
        selectBeverageButton.setProperties(this, width / NUM_BUTTONS, BUTTON_HEIGHT);
        buttonPanel.add(selectBeverageButton, BorderLayout.SOUTH);

        panel.add(buttonPanel, BorderLayout.NORTH);
        balanceLabel.setPreferredSize(new Dimension(width, (HEIGHT - BUTTON_HEIGHT) / 2));
        panel.add(balanceLabel, BorderLayout.CENTER);
        msgLabel.setPreferredSize(new Dimension(width, (HEIGHT - BUTTON_HEIGHT) / 2));
        panel.add(msgLabel, BorderLayout.SOUTH);
        setBalanceText(); // 투입 금액 0
        return panel;
    }

    public void setBalanceText() {
        balanceLabel.setText("현재 투입 금액: " + balance + "원");
    }

    public void setMsgText(String msg) {
        msgLabel.setText(msg);
    }

    public void setState(State state) {
        curState = state;
    }

    public State0 getState0() {
        return state0;
    }

    public State500 getState500() {
        return state500;
    }

    public StateLess500 getStateLess500() {
        return stateLess500;
    }

    public StateLess1000 getStateLess1000() {
        return stateLess1000;
    }

    public StateEqualOrMore1000 getStateEqualOrMore1000() {
        return stateEqualOrMore1000;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hundredButton) {
            curState.addHundred();
        }
        else if (e.getSource() == fiveHundredButton) {
            curState.addFiveHundred();
        }
        else if (e.getSource() == thousandButton) {
            curState.addThousand();
        }
        else if (e.getSource() == returnChangesButton) {
            curState.returnChanges();
        }
        else if (e.getSource() == selectBeverageButton) {
            curState.selectBeverage();
        }
    }
}