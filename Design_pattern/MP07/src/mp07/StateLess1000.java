package mp07;

public class StateLess1000 implements State{
    private MainWindow mainWindow;
    private int balance;

    public StateLess1000(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void returnChanges() {
        balance = mainWindow.getBalance();
        mainWindow.setMsgText("" + balance + "원을 반환합니다");
        mainWindow.setBalance(0);
        mainWindow.setBalanceText();
        mainWindow.setState(mainWindow.getState0());
    }

    @Override
    public void selectBeverage() {
        mainWindow.setMsgText("1000원 이상을 넣은 후에 눌러주세요");
    }

    @Override
    public void addHundred() {
        balance = mainWindow.getBalance();
        balance += 100;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");

        if(balance == 1000)
            mainWindow.setState(mainWindow.getStateEqualOrMore1000());
    }

    @Override
    public void addFiveHundred() {
        balance = mainWindow.getBalance();
        balance += 500;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");
        mainWindow.setState(mainWindow.getStateEqualOrMore1000());
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