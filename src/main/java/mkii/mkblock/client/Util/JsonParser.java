package mkii.mkblock.client.Util;

import mkii.mkblock.client.common.Configure;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonParser {
    public FileReader fileReader;

    public JsonParser(String filePath){
        try {
            this.fileReader = new FileReader(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Configure getConfigureJSON() {
        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(this.fileReader);

            JSONObject block = (JSONObject) jsonObject.get("block");
            JSONObject env = (JSONObject) jsonObject.get("env");
            JSONObject network = (JSONObject) jsonObject.get("network");

            return new Configure(block.get("address").toString(), env.get("contract").toString(), env.get("path").toString(), env.get("source_folder").toString(), env.get("lang").toString(),
                    env.get("project").toString(), env.get("contract").toString(), env.get("compile").toString(), network.get("ip").toString(), network.get("port").toString(), block.get("taxi").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
