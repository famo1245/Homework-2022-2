package mid01;

public class Main {
    public static final String HOUSE = "house.png";

    public static void main(String[] args) {
        ImageConverter converter = new ImageConverter(new GreyImageConverter());
        converter.convertImage(HOUSE);
        converter.setConverter(new InverseImageConverter());
        converter.convertImage(HOUSE);
        converter.setConverter(new SepiaImageConverter());
        converter.convertImage(HOUSE);
    }
}
