package elevator;

public class ControlRoomDisplay implements Observer {
    @Override
    public void update(int n) {
        System.out.println("Display : " + n + "층");
    }
}
