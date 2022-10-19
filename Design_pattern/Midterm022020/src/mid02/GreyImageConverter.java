package mid02;

import java.awt.image.*;
//import java.awt.Image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

//import java.imageio.*;

// 흑백 영상으로 변환하는 코드
public class GreyImageConverter extends IColorConverterDecorator {

    public GreyImageConverter(IColorConverter converter) {
        super(converter);
    }

    public Color getColor(Color color) {
        super.getColor(color);
        int outputRed = (color.getRed() + color.getGreen() + color.getBlue())  / 3;
        int outputGreen = (color.getRed() + color.getGreen() + color.getBlue())  / 3;
        int outputBlue = (color.getRed() + color.getGreen() + color.getBlue())  / 3;
        color = new Color(outputRed, outputGreen, outputBlue);
        return color;
    }

    @Override
    public String getExtension() {
        return "g_" + super.getExtension();
    }
}