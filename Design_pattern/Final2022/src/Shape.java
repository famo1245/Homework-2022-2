public class Shape implements Selectable {
    private String name;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Shape(String name) {
        this.name = name;
    }

    @Override
    public int getMinBoundsX() {
        return minX;
    }

    @Override
    public int getMinBoundsY() {
        return minY;
    }

    @Override
    public int getMaxBoundsX() {
        return maxX;
    }

    @Override
    public int getMaxBoundsY() {
        return maxY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isSelected(int x, int y) {
        return ((x <= maxX && x >= minX) && (y <= maxY && y >= minY));
    }

    @Override
    public void print() {
        System.out.println(getName() + ":");
        System.out.println("minBoundsX: " + getMinBoundsX() + ", minBoundsY: " + getMinBoundsY());
        System.out.println("maxBoundsX: " + getMaxBoundsX() + ", maxBoundsY: " + getMaxBoundsY());
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }
}
