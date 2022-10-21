package no1;

public class Group extends ShapeDecorator {
    Shape newShape;
    public Group(String name, Shape shapeComponent, Shape newShape) {
        super(name, shapeComponent);
        this.newShape = newShape;
    }

    @Override
    public void calcBounds() {
        newShape.calcBounds();
        super.calcBounds();
        if (super.shapeComponent.getMinBoundsX() > newShape.getMinBoundsX()) {
            super.setMinBoundsX(newShape.getMinBoundsX());
        } else {
            super.setMinBoundsX(super.shapeComponent.getMinBoundsX());
        }

        if (super.shapeComponent.getMaxBoundsX() < newShape.getMaxBoundsX()) {
            super.setMaxBoundsX(newShape.getMaxBoundsX());
        } else {
            super.setMaxBoundsX(super.shapeComponent.getMaxBoundsX());
        }

        if (super.shapeComponent.getMinBoundsY() > newShape.getMinBoundsY()) {
            super.setMinBoundsY(newShape.getMinBoundsY());
        } else {
            super.setMinBoundsY(super.shapeComponent.getMinBoundsY());
        }
        if (super.shapeComponent.getMaxBoundsY() < newShape.getMaxBoundsY()) {
            super.setMaxBoundsY(newShape.getMaxBoundsY());
        } else {
            super.setMaxBoundsY(super.shapeComponent.getMaxBoundsY());
        }
    }

    @Override
    public String getShapeName() {
        return super.getShapeName();
    }

    @Override
    public String toString() {
        return super.toString() + newShape.toString() + shapeComponent.toString();
    }
}
