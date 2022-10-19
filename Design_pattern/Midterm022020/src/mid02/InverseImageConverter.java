package mid02;

import java.awt.image.*;
//import java.awt.Image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

//import java.imageio.*;

public class InverseImageConverter extends IColorConverterDecorator {
    public InverseImageConverter(IColorConverter converter) {
        super(converter);
    }

    @Override
    public Color getColor(Color color) {
        color = super.getColor(color);
        int outputRed = 255 - color.getRed();
        int outputGreen = 255 - color.getGreen();
        int outputBlue = 255 - color.getBlue();
        color = new Color(outputRed, outputGreen, outputBlue);
        return color;
    }

    @Override
    public String getExtension() {
        return "i_" + super.getExtension();
    }
}