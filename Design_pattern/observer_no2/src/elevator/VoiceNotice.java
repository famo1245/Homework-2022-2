package elevator;

public class VoiceNotice implements Observer {
    @Override
    public void update(int n) {
        System.out.println(n + "층 입니다");
    }
}
