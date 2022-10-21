package no1;

public class ShapeFactory implements ShapeFactoryInterface {
    @Override
    public Shape createShape(String type, String name, int min, int max) {
        if(type.equals("Rectangle")) {
            return new Rectangle(name, min, max);
        } else if (type.equals("Triangle")) {
            return new Triangle(name, min, max);
        }
        return null;
    }
}
