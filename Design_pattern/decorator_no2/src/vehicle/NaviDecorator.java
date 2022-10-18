package vehicle;

public class NaviDecorator extends CarOptionDecorator {
    private int naviPrice;

    public NaviDecorator(CarComponent carComponent, int price) {
        super(carComponent);
        naviPrice = price;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + naviPrice;
    }

    @Override
    public String getCarinfo() {
        return super.getCarinfo() + " NAVI";
    }

    public int getNaviPrice() {
        return naviPrice;
    }
}
