package mp04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LoadHudDisplays implements LoadDisplayInterface {
    BufferedReader reader;
    String fileName;
    public LoadHudDisplays(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<String> load() {
        ArrayList<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String data = reader.readLine();
            while(data != null) {
                list.add(data);
                data = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
