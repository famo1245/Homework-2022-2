package elevator;

public class Main { //evfc
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController();
        controller.registerObserver(new ElevatorDisplay());
        controller.registerObserver(new VoiceNotice());
        controller.registerObserver(new FloorDisplay());
        controller.registerObserver(new ControlRoomDisplay());

        controller.gotoFloor(5);
        controller.gotoFloor(10);
    }
}
