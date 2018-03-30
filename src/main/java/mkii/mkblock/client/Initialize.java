package mkii.mkblock.client;

import mkii.mkblock.client.Util.MakeFolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Initialize {
    public static void initialize() {

        // make configure file
        makeJSONConfig();

        // make binary folder
        MakeFolder.makeFolder("build");
        MakeFolder.makeFolder("lib");
        MakeFolder.makeFolder("src");


    }

    public static void makeJSONConfig() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("name", "mkblock client");
            obj.put("author", "Kenneth Kwon");
            obj.put("version", "0.1.0");
            obj.put("main", "index.js");
            obj.put("license", "MIT");

            obj.put("lang", "python3");
            obj.put("default", "src");
            obj.put("init", "init.py");

            // try-with-resources statement based on post comment below :)
            try (FileWriter file = new FileWriter("config.json")) {
                file.write(obj.toJSONString().replace(",", ", \n").replace("{", "{\n").replace("}", "\n}"));
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: " + obj);
                file.flush();
                file.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
