package mkii.mkblock.client;

import mkii.mkblock.client.Util.MakeHash;
import mkii.mkblock.client.common.Configure;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

import static mkii.mkblock.client.Util.MakeHash.*;
import static mkii.mkblock.client.common.Constants.*;

public class Compile {

    /**
     *
     * @param conf
     * @return
     */
    public static String getSourceCompile(Configure conf) {

        try {
            String compilePath = conf.lang + " " + conf.dirPath + conf.projectName + "/" + conf.compile;
//            System.out.println("--> " + compilePath);
            Process process = Runtime.getRuntime().exec(compilePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String ret;
            while((ret = in.readLine()) != null) {
//                if(ret.equals("COMPLETED!!!!!!")) {
//                    File file = new File("dump.cb");
//                    String forHash="";
//                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                        String line;
//                        while ((line = br.readLine()) != null) {
//                            // process the line.
//                            System.out.println("--> " + line);
//                            if(forHash.equals("")) forHash += line;
//                            else forHash += "," + line;
//                        }
//                    }
//
//                    String hashCode = MakeHash.getHashCode(new Timestamp(System.currentTimeMillis()) + forHash);
//                    System.out.println("hashcode : " + hashCode);
//
//                    // make contract file
//                    File pw = new File("contract.dat");
//                    if(!pw.exists()) pw.createNewFile();
//                    FileOutputStream outputStream = new FileOutputStream(pw, true);
//                    outputStream.write((address+":"+hashCode+":"+forHash).getBytes());
//                    outputStream.write(System.getProperty("line.separator").getBytes());
//                    outputStream.flush();
//                    outputStream.close();
//                    return address+":"+hashCode+":"+forHash;
//                }
                System.out.println("Compiling... [" + ANSI_GREEN + "OK" + ANSI_RESET + "]");
                return ret.replace("b'", "").replace("\'", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param sourcepath
     */
    public static String convertSourcecode(String sourcepath)  {
        File file = new File(sourcepath);
        String forHash="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                //System.out.println("--> " + line);
                forHash += line;
            }
            String hex = byteArrayToHex(forHash.getBytes());
            System.out.println("Source Hex : " + hex);

            byte[] b = hexToByteArray(hex);

            //System.out.println("String : " + new String(b));
//            return new String(b);
            return hex;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
