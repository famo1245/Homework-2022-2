package mp09;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public abstract class TemplateImageConverter {
    //template method
    public void convertImage(String fileName) {
        // 전체적인 이미지 파일 입출력 과정에서 IOException이 발생할 수 있어 예외 처리 코드를 넣음
        try {
            // 이미지 파일 데이터를 메모리에 넣음
            BufferedImage image = ImageIO.read(new File(fileName));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Color color = new Color(image.getRGB(x, y));
                    // 변환 방식이 다르므로 setColor 함수 정의
                    image.setRGB(x, y, setColor(color).getRGB());
                }
            }

            // 변환 방식에 따라 파일명 앞에 붙는 이름이 다르므로 getExtension 함수 정의
            ImageIO.write(image, "png", new File(getExtension() + fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract Color setColor(Color color);
    public abstract String getExtension();
}
