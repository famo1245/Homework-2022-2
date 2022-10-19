package mid02;

import java.awt.*;

public class OriginalColorConverter implements IColorConverter {

    @Override
    public Color getColor(Color color) {
        return color;
    }

    @Override
    public String getExtension() {
        return "";
    }
}
