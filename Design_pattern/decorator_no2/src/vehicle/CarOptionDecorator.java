package vehicle;

public abstract class CarOptionDecorator extends CarComponent {
    private CarComponent carComponent;
    public CarOptionDecorator(CarComponent carComponent) {
        this.carComponent = carComponent;
    }
    @Override
    public int getPrice() {
        return carComponent.getPrice();
    }

    @Override
    public String getCarinfo() {
        return carComponent.getCarinfo();
    }
}
