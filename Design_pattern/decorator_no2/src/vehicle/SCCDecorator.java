package vehicle;

public class SCCDecorator extends CarOptionDecorator {
    private int sccPrice;

    public SCCDecorator(CarComponent carComponent, int price) {
        super(carComponent);
        sccPrice = price;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + sccPrice;
    }

    @Override
    public String getCarinfo() {
        return super.getCarinfo() + " SCC";
    }

    public int getSccPrice() {
        return sccPrice;
    }
}
