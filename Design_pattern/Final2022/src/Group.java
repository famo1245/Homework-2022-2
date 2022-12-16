import java.util.ArrayList;

public class Group implements Selectable {
    private int groupNum;
    private ArrayList<Shape> shapes;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Group(int groupNum) {
        this.groupNum = groupNum;
        shapes = new ArrayList<>();
        maxX = -1;
        maxY = -1;
        minX = 10000;
        minY = 10000;
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
        return "Group: " + groupNum;
    }

    @Override
    public boolean isSelected(int x, int y) {   // 구현 못함
        boolean selected = true;
        for(Shape s : shapes) {
            selected = (selected && s.isSelected(x, y));
        }

        return selected;
    }

    @Override
    public void print() {
        System.out.println(getName());
        System.out.println("NumOfSelectables: " + shapes.size());
        System.out.println("minBoundsX: " + getMinBoundsX() + ", minBoundsY: " + getMinBoundsY());
        System.out.println("maxBoundsX: " + getMaxBoundsX() + ", maxBoundsY: " + getMaxBoundsY());
        for(Shape s : shapes)
            s.print();
    }

    public void addShape(Shape s) {
        shapes.add(s);
        calcBounds(s.getMaxBoundsX(), s.getMaxBoundsY(), s.getMinBoundsX(), s.getMinBoundsY());
    }

    private void calcBounds(int maxx, int maxy, int minx, int miny) {
        maxX = Math.max(maxx, maxX);
        maxY = Math.max(maxy, maxY);
        minX = Math.min(minx, minX);
        minY = Math.min(miny, minY);
    }
}
