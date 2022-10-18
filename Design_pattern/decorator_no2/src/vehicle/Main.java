package vehicle;

public class Main {
    public static void main(String[] args) {
        CarComponent carComponent = new BasicCar(2000);
        carComponent = new AirBagDecorator(carComponent, 500);
        carComponent = new ESCDecorator(carComponent, 100);
        carComponent = new NaviDecorator(carComponent, 200);
        carComponent = new SCCDecorator(carComponent, 150);
        System.out.println("Price : " + carComponent.getPrice());
        System.out.println("Car Info : " + carComponent.getCarinfo());
    }
}
