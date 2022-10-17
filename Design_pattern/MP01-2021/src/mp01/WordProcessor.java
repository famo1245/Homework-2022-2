package mp01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordProcessor {
    private ISpellChecker spellChecker;
    private Map<String, DocConverter> docConveters;
    private String fileName;

    public WordProcessor(String fileName) {
        this.fileName = fileName;
        docConveters = new HashMap<String, DocConverter>();
    }

    public void addDocConverter(DocConverter converter) {
        docConveters.put(converter.getExtension(), converter);
    }

    public void convertDocTo(String ext) {
        DocConverter tmp = docConveters.get(ext);

        if (tmp != null) {
            tmp.save(fileName);
            return;
        }
        System.out.println(ext + "파일 형식을 지원하는 DocConverter가 없습니다");
    }

    public void setSpellChecker(ISpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void checkSpelling() {
        this.spellChecker.check();
    }
}
