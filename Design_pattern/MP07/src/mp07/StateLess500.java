package mp07;

public class StateLess500 implements State {
    private MainWindow mainwindow;

    public StateLess500(MainWindow mainwindow) {
        this.mainwindow = mainwindow;
    }

    @Override
    public void returnChanges() {
        int balance = mainwindow.getBalance();
        mainwindow.setMsgText("" + balance + "원을 반환합니다");
        mainwindow.setBalance(0);
        mainwindow.setBalanceText();
        mainwindow.setState();
    }

    @Override
    public void selectBeverage() {
        mainwindow.setMsgText("1000원 이상을 넣은 후에 눌러주세요");
    }

    @Override
    public void addHundred() {

    }

    @Override
    public void addFiveHundred() {

    }

    @Override
    public void addThousand() {

    }
}
