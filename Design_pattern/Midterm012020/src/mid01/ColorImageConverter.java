package mid01;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ColorImageConverter {
    public Color getColor(Color color);
    public String getExtension();
}
