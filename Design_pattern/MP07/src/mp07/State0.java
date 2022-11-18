package mp07;

public class State0 implements State {
    MainWindow mainWindow;

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

    }

    @Override
    public void addFiveHundred() {

    }

    @Override
    public void addThousand() {

    }
}
