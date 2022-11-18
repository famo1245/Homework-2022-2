package mp07;

public class StateController {
    MainWindow mainWindow;
    State currentState;
    State0 state0;
    StateLess500 state_less_500;

    public StateController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        state0 = new State0(mainWindow);
        state_less_500 = new StateLess500(mainWindow);
    }


}
