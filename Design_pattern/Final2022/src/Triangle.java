public class Triangle extends Shape {
    private int x[];
    private int y[];
    public Triangle(String name, int[] coords, int idx) {
        super(name);
        x = new int[3];
        y = new int[3];
        int j=0;
        for(int i=idx; i < idx+6; i+=2) {
            x[j] = coords[i];
            j++;
        }

        j = 0;
        for(int i=idx+1; i < idx+6; i+=2) {
            y[j] = coords[i];
            j++;
        }

        calcBounds();
    }

    private void calcBounds() {
        int maxX = Math.max(Math.max(x[0],x[1]), x[2]);
        int maxY = Math.max(Math.max(y[0],y[1]), y[2]);
        int minX = Math.min(Math.min(x[0],x[1]), x[2]);
        int minY = Math.min(Math.min(y[0],y[1]), y[2]);

        setMaxX(maxX);
        setMaxY(maxY);
        setMinX(minX);
        setMinY(minY);
    }
}
