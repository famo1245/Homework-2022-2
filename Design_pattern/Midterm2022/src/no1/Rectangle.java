package no1;

public class Rectangle extends Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String shapeName;

    public Rectangle(String name, int min, int max) {
        shapeName = name;
        x1 = MyRandom.randInt(min, max);
        y1 = MyRandom.randInt(min, max);
        x2 = x1 + 30;
        y2 = y1 + 20;
    }

    @Override
    public void calcBounds() {
        if (x1 <= x2) {
            super.setMinBoundsX(x1);
            super.setMaxBoundsX(x2);
        } else {
            super.setMinBoundsX(x2);
            super.setMaxBoundsX(x1);
        }

        if (y1 <= y2) {
            super.setMinBoundsY(y1);
            super.setMaxBoundsY(y2);
        } else {
            super.setMinBoundsY(y2);
            super.setMaxBoundsY(y1);
        }
    }

    @Override
    public String getShapeName() {
        return shapeName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
