package mp07;

public class StateEqualOrMore1000 implements State {
    private MainWindow mainWindow;
    private int balance;

    public StateEqualOrMore1000(MainWindow mainWindow) {
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
        String msg = "음료를 내보냅니다. 배출구를 확인하세요.";
        balance = mainWindow.getBalance();
        balance -= 1000;
        if (balance > 0) {
            msg = msg + " 거스름돈 " + balance + "원을 반환합니다.";
            balance = 0;
        }
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText(msg);
        mainWindow.setState(mainWindow.getState0());
    }

    @Override
    public void addHundred() {
        balance = mainWindow.getBalance();
        balance += 100;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");
    }

    @Override
    public void addFiveHundred() {
        balance = mainWindow.getBalance();
        balance += 500;
        mainWindow.setBalance(balance);
        mainWindow.setBalanceText();
        mainWindow.setMsgText("");
    }

    @Override
    public void addThousand() {
        mainWindow.setMsgText("이미 충분한 돈이 투입되었습니다. 음료를 선택하세요");
    }
}