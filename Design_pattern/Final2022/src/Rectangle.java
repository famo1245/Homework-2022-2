public class Rectangle extends Shape {
    int x[];
    int y[];
    public Rectangle(String name, int[] coords, int idx) {
        super(name);
        x = new int[2];
        y = new int[2];
        int j = 0;
        for(int i = idx; i < idx + 4; i += 2) {
            x[j] = coords[i];
            j++;
        }
        j = 0;
        for(int i=idx+1; i<idx+4; i+= 2) {
            y[j] = coords[i];
            j++;
        }

        calcBounds();
    }

    private void calcBounds() {
        int maxX = Math.max(x[0], x[1]);
        int maxY = Math.max(y[0], y[1]);
        int minX = Math.min(x[0], x[1]);
        int minY = Math.min(y[0], y[1]);

        setMaxX(maxX);
        setMaxY(maxY);
        setMinX(minX);
        setMinY(minY);
    }
}
