package example1;

public class LeftBehavior implements CarMoveBehavior {
    @Override
    public void action() {
        System.out.println("Left!");
    }
}
