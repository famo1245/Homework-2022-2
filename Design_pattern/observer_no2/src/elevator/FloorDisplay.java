package elevator;

public class FloorDisplay implements Observer {
    @Override
    public void update(int n) {
        System.out.println("Floor Display : " + n);
    }
}
