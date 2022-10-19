package mid01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    ColorImageConverter converter;

    public ImageConverter(ColorImageConverter converter) {
        this.converter = converter;
    }

    public void setConverter(ColorImageConverter converter) {
        this.converter = converter;
    }

    public void convertImage(String HOUSE) {
        try {
            BufferedImage image = ImageIO.read(new File(HOUSE));

            for(int y = 0; y < image.getHeight(); y++) {
                for(int x = 0; x < image.getWidth(); x++) {
                    Color color = new Color(image.getRGB(x, y));
                    color = converter.getColor(color);
                    image.setRGB(x, y, color.getRGB());
                }
            }

            ImageIO.write(image, "png", new File(converter.getExtension() + HOUSE));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}