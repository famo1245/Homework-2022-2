package kbo;

public abstract class KBO {
    private HomeTown homeTown;
    private Established established;

    public abstract void display();

    public void active() {
        System.out.println("2015년 이후로 10개 구단 체제로 리그가 진행중입니다!");
    }

    public void setHomeTown(HomeTown homeTown) {
        this.homeTown = homeTown;
    }

    public void performHomeTown() {
        homeTown.home();
    }

    public void setEstablished(Established est) {
        this.established = est;
    }

    public void performEst() {
        established.est();
    }
}
