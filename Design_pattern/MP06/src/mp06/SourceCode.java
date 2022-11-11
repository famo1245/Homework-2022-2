package mp06;

public class SourceCode {
    private String sourceCodeName;

    public SourceCode(String sourceCodeName) {
        if(sourceCodeName.contains(".c")) {
            this.sourceCodeName = sourceCodeName;
            return;
        }

        this.sourceCodeName = null;
    }

    public String getSourceCodeName() {
        return sourceCodeName;
    }

    @Override
    public String toString() {
        return sourceCodeName;
    }
}
