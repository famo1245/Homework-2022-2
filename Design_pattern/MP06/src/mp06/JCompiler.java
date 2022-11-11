package mp06;

public class JCompiler implements Compiler {
    @Override
    public ObjectCode compile(SourceCode source) {
        System.out.println("Compiling code: " + source);
        String sourceName = source.getSourceCodeName();
        sourceName = sourceName.substring(0, sourceName.length() - 2);

        // ObjectCode로 변환
        String convertForm = "%s.obj";
        String objName = String.format(convertForm, sourceName);
        ObjectCode objFile = new ObjectCode(objName);
        System.out.println("Generating object code: " + objFile);

        return objFile;
    }
}
