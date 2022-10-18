package vehicle;

public class ESCDecorator extends CarOptionDecorator {
    private int escPrice;

    public ESCDecorator(CarComponent carComponent, int price) {
        super(carComponent);
        escPrice = price;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + escPrice;
    }

    @Override
    public String getCarinfo() {
        return super.getCarinfo() + " ESC";
    }

    public int getEscPrice() {
        return escPrice;
    }
}
