package mp06;

import java.util.Arrays;
import java.util.List;

public class JLinker implements Linker {
    @Override
    public Executable link(String exeFileName, ObjectCode[] objFiles) {
        return link(exeFileName, Arrays.asList(objFiles));
    }

    @Override
    public Executable link(String exeFileName, List<ObjectCode> objFiles) {
        String linkedFiles = "";
        System.out.print("Linking");
        for(ObjectCode o : objFiles) {
            linkedFiles = linkedFiles + "\n" + o.getObjFileName();
        }
        System.out.println(linkedFiles);
        return new Executable(exeFileName, linkedFiles);
    }
}
