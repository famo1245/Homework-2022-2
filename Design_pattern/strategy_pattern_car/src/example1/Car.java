package example1;

public class Car {
    private CarMoveBehavior carMoveBehavior;

    public Car(CarMoveBehavior behavior) {
        this.carMoveBehavior = behavior;
    }

    public void move() {
        carMoveBehavior.action();
    }

    public void setCarMoveBehavior(CarMoveBehavior carMoveBehavior) {
        this.carMoveBehavior = carMoveBehavior;
    }
}
