package no1;

public class Triangle extends Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;

    private String shapeName;

    public Triangle(String name, int min, int max) {
        shapeName = name;
        x1 = MyRandom.randInt(min, max);
        y1 = MyRandom.randInt(min, max);
        x2 = MyRandom.randInt(min, max);
        y2 = MyRandom.randInt(min, max);
        x3 = MyRandom.randInt(min, max);
        y3 = MyRandom.randInt(min, max);
    }

    @Override
    public void calcBounds() {
        int[] x = { x1, x2, x3 };
        int[] y = { y1, y2, y3 };
        int minX = 1000;
        int minY = 1000;
        int maxX = 0;
        int maxY = 0;

        for (int i=0; i<x.length; i++) {
            if(x[i] < minX) {
                minX = x[i];
            }
            if(x[i] > maxX) {
                maxX = x[i];
            }
        }

        for (int i=0; i<y.length; i++) {
            if(y[i] < minY) {
                minY = y[i];
            }
            if(y[i] > maxY) {
                maxY = y[i];
            }
        }
        super.setMinBoundsX(minX);
        super.setMinBoundsY(minY);
        super.setMaxBoundsX(maxX);
        super.setMaxBoundsY(maxY);
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
