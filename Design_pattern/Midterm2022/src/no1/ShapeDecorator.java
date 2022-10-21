package no1;

public abstract class ShapeDecorator extends Shape {
    Shape shapeComponent;
    String shapeName;

    public ShapeDecorator(String shapeName, Shape shapeComponent) {
        this.shapeName = shapeName;
        this.shapeComponent = shapeComponent;
    }
    @Override
    public void calcBounds() {
        shapeComponent.calcBounds();
    }

    @Override
    public String getShapeName() {
        return this.shapeName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
