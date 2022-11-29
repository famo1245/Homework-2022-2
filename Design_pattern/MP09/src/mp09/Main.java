package mp09;

public class Main {
    public static void main(String[] args) {
        final String HOUSE = "house.png";
        TemplateImageConverter converter = new GreyImageConverter();
        converter.convertImage(HOUSE);
        converter = new InverseImageConverter();
        converter.convertImage(HOUSE);
        converter = new SepiaImageConverter();
        converter.convertImage(HOUSE);
    }
}
