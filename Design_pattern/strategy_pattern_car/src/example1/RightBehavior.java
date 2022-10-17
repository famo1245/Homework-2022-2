package example1;

public class RightBehavior implements CarMoveBehavior {
    @Override
    public void action() {
        System.out.println("Right!");
    }
}
