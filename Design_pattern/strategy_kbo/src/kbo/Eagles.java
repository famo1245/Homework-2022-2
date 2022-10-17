package kbo;

public class Eagles extends KBO {
    public Eagles() {
        setHomeTown(new NonCapital());
        setEstablished(new Origin());
    }
    @Override
    public void display() {
        System.out.println("한화 이글스");
    }
}
