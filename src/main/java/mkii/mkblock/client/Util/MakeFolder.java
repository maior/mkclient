package mkii.mkblock.client.Util;

import java.io.File;

public class MakeFolder {
    public static void makeFolder(String name) {
        File folder = new File(name);
        if(!folder.exists()) {
            folder.mkdir();
        }
    }
}
