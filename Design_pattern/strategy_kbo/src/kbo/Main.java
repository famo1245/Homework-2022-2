package kbo;

public class Main {
    public static void main(String[] args) {
        KBO baseball = new Eagles();
        baseball.active();
        baseball.display();
        baseball.performEst();
        baseball.performHomeTown();

        baseball = new Heroes();
        baseball.display();
        baseball.performEst();
        baseball.performHomeTown();
    }
}
