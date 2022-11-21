package mp07;

public class State0 implements State {
    private MainWindow mainWindow;
    private int balance;

    public State0(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void returnChanges() {
        mainWindow.setMsgText("돈을 넣은 후에 눌러주세요");
    }

    @Override
    public void selectBeverage() {
        mainWindow.setMsgText("돈을 넣은 후에 눌러주세요");
    }

    @Override
    public void addHundred() {
        balance = mainWindow.getBalance();
        balance += 100;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");
        mainWindow.setState(mainWindow.getStateLess500());
    }

    @Override
    public void addFiveHundred() {
        balance = mainWindow.getBalance();
        balance += 500;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");
        mainWindow.setState(mainWindow.getStateLess1000());
    }

    @Override
    public void addThousand() {
        balance = mainWindow.getBalance();
        balance += 1000;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");
        mainWindow.setState(mainWindow.getStateEqualOrMore1000());
    }
}