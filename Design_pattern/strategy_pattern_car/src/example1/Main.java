package example1;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(new UpBehavior());
        car.move();
        car.setCarMoveBehavior(new DownBehavior());
        car.move();
        car.setCarMoveBehavior(new LeftBehavior());
        car.move();
        car.setCarMoveBehavior(new RightBehavior());
        car.move();
    }
}
