package vehicle;

public class BasicCar extends CarComponent {
    private int price;

    public BasicCar(int price) {
        this.price = price;
    }
    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getCarinfo() {
        return "Basic car";
    }
}
