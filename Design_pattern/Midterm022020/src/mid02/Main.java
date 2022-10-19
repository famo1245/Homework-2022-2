package mid02;

public class Main {
    public static final String HOUSE = "house.png";

    public static void main(String[] args) {
        IColorConverter converter;
        converter = new OriginalColorConverter();
        converter = new SepiaImageConverter(converter);
        converter = new InverseImageConverter(converter);
        ImageConverter imageConverter = new ImageConverter(converter);
        imageConverter.convertImage(HOUSE);
        converter = new OriginalColorConverter();
        
        converter = new GreyImageConverter(converter);
        converter = new InverseImageConverter(converter);
        imageConverter.setConverter(converter);
        imageConverter.convertImage(HOUSE);

        converter = new OriginalColorConverter();
        converter = new InverseImageConverter(converter);
        converter = new SepiaImageConverter(converter);
        imageConverter.setConverter(converter);
        imageConverter.convertImage(HOUSE);

    }
}
