package no1;

public abstract class Shape {
    private int minBoundsX;
    private int minBoundsY;
    private int maxBoundsX;
    private int maxBoundsY;

    public int getMinBoundsX() { return minBoundsX; }
    public int getMinBoundsY() { return minBoundsY; }
    public int getMaxBoundsX() { return maxBoundsX; }
    public int getMaxBoundsY() { return maxBoundsY; }

    public void setMinBoundsX(int minX) {
        minBoundsX = minX;
    }

    public void setMinBoundsY(int minY) {
        minBoundsY = minY;
    }

    public void setMaxBoundsX(int maxX) {
        maxBoundsX = maxX;
    }

    public void setMaxBoundsY(int maxY) {
        maxBoundsY = maxY;
    }

    public String toString() {
        return getShapeName() + ":\n" + "minBoundsX: " + getMinBoundsX() + ", minBoundsY: " + getMinBoundsY() + "\n"
                + "maxBoundsX: " + getMaxBoundsX() + ", maxBoundsY: " + getMaxBoundsY() + "\n";
    }

    public abstract void calcBounds();

    public abstract String getShapeName();
}
