package mid01;

import java.awt.image.*;
//import java.awt.Image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

//import java.imageio.*;

public class SepiaImageConverter implements ColorImageConverter {
    @Override
    public Color getColor(Color color) {
        int grey = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        int outputRed = 230 * grey / 255;
        int outputGreen = 180 * grey / 255;
        int outputBlue =  150 * grey / 255;
        color = new Color(outputRed, outputGreen, outputBlue);
        return color;
    }

    @Override
    public String getExtension() { return "s_"; }
}