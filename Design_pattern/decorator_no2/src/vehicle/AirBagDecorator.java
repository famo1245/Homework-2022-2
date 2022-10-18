package vehicle;

public class AirBagDecorator extends CarOptionDecorator {
    private int airBagPrice;

    public AirBagDecorator(CarComponent carComponent, int price) {
        super(carComponent);
        airBagPrice = price;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + airBagPrice;
    }

    @Override
    public String getCarinfo() {
        return super.getCarinfo() + " AirBag";
    }

    public int getAirBagPrice() {
        return airBagPrice;
    }
}
