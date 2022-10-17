package mp01;

public class OdtDocConverter extends DocConverter {
    public OdtDocConverter() {
        super("odt");
    }

    @Override
    public void save(String fileName) {
        System.out.println(fileName + "." + getExtension() + "로 변환해서 저장합니다");
    }
}
