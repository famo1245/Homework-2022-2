package mp09;

import java.awt.image.*;
//import java.awt.Image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

//import java.imageio.*;

// 흑백 영상으로 변환하는 코드
class GreyImageConverter extends TemplateImageConverter {
    @Override
    public Color setColor(Color color) {
        int outputRed = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        int outputGreen = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        int outputBlue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return new Color(outputRed, outputGreen, outputBlue);
    }

    @Override
    public String getExtension() {
        return "g_";
    }
}