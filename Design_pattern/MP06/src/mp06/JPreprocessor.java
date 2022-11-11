package mp06;

public class JPreprocessor implements Preprocessor{
    @Override
    public SourceCode preprocess(SourceCode source) {
        System.out.println("Preprocessing code: " + source);
        String sourceName = source.getSourceCodeName();

        // 전처리한 SourceCode 객체 생성
        String convertForm = "preprocessed_%s";
        String convertedName = String.format(convertForm, sourceName);
        SourceCode preprocessedCode = new SourceCode(convertedName);
        System.out.println("Generating a new code: " + preprocessedCode);

        return preprocessedCode;
    }
}
