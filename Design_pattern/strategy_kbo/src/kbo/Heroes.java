package kbo;

public class Heroes extends KBO {
    public Heroes() {
        setHomeTown(new Capital());
        setEstablished(new NonOrigin());
    }
    @Override
    public void display() {
        System.out.println("키움 히어로즈");
    }
}
