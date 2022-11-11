package mp06;

import java.util.ArrayList;
import java.util.List;

public class IDE implements Builder {
    private String projectName;
    private Preprocessor preprocessor;
    private Compiler compiler;
    private Linker linker;

    public IDE(Preprocessor preprocessor, Compiler compiler, Linker linker) {
        this.preprocessor = preprocessor;
        this.compiler = compiler;
        this.linker = linker;
    }

    @Override
    public void createProject(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public Executable build(SourceCode[] sourceCodes) {
        ObjectCode[] objectCodes = new ObjectCode[sourceCodes.length];

        //Preprocessing & Compiling
        for(int i=0; i<sourceCodes.length; i++) {
            SourceCode temp = preprocessor.preprocess(sourceCodes[i]);
            objectCodes[i] = compiler.compile(temp);
        }

        //linking
        return linker.link(projectName + ".exe", objectCodes);
    }

    @Override
    public Executable build(List<SourceCode> sourceCodes) {
        List<ObjectCode> objectCodes = new ArrayList<>();
        //Preprocessing & Compiling
        for(SourceCode s : sourceCodes) {
            SourceCode temp = preprocessor.preprocess(s);
            objectCodes.add(compiler.compile(temp));
        }

        //linking
        return linker.link(projectName + ".exe", objectCodes);
    }
}
