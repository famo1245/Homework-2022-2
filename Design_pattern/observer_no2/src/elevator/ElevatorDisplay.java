package elevator;

public class ElevatorDisplay implements Observer {
    @Override
    public void update(int n) {
        System.out.println("Current Floor : " + n);
    }
}
