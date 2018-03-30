package mkii.mkblock.client;

import mkii.mkblock.client.Util.JsonParser;
import mkii.mkblock.client.Util.MakeHash;
import mkii.mkblock.client.common.Configure;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

import static mkii.mkblock.client.Util.MakeHash.byteArrayToHex;
import static mkii.mkblock.client.Util.MakeHash.hexToByteArray;
import static mkii.mkblock.client.common.Constants.*;

public class Main {

    public static String publicKey;
    public static Socket socket;
    public static OutputStream output;
    public static JsonParser jsonParser;
    public static Configure configure;
    public static boolean isSocketAlive;

    /**
     * Main!!!
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("=========== MKII Blockchain Client ===========");

        try {
            System.out.print("Loading configure... ");
            jsonParser = new JsonParser(CONFIGURE_FILE);
            configure = jsonParser.getConfigureJSON();
            System.out.println("[ " + ANSI_GREEN + "OK" + ANSI_RESET + " ]");

            publicKey = configure.publicKey;

            // connect localhost
            System.out.print("Connecting mkblock... ");
            //socket = new Socket(configure.ip.toString(), Integer.getInteger(configure.port));
            socket = new Socket("localhost", 8788);
            System.out.println("[ " + ANSI_GREEN + "OK" + ANSI_RESET + " ]");
            output = socket.getOutputStream();
            isSocketAlive = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ " + ANSI_GREEN + "ERROR" + ANSI_RESET + " ]");
            System.out.println(ANSI_RED + "Counld not connect RPC daemon!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Please check your RPC daemon!!!!" + ANSI_RESET);
            isSocketAlive = false;
            return;
        }

        System.out.println("Usage : exit or quit");

        String contractCode;
        String contractHexCode;
        String sourceHexCode;

        if(args.length > 0) {
            System.out.println("args : " + args[0]);
            if(args[0].equals("init")){
                Initialize.initialize();
            } else if(args[0].equals("compile")){

                // make compile
                getCompile();

//                if(ADDRESS.length() > 0) Compile.getSourceCompile(ADDRESS);
//                else System.out.println("registerAddress first!!!!!!");
            } else if(args[0].equals("registerAddress")){
                registerAddress(args);
            }
        }

        try {


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.print(">> ");
                String s = br.readLine();
                if(s.equals("quit") || s.equals("exit")) {
                    System.out.println("Good Bye!");
                    break;
                }

                String[] inStr = s.split(" ");

                if(inStr[0].equals("deploy")) {

                } else if(inStr[0].equals("compile")) {
                    getCompile();
                } else if(inStr[0].equals("registerAddress")) {
                    registerAddress(inStr);
                } else if(inStr[0].equals("init")) {
                    // initializing folder & etc
                    Initialize.initialize();
                } else {
                    System.out.println("send : " + inStr[0]);
                    sendData(s);
                }


            }
            br.close();
        } catch (IOException ioe) {

            //ioe.printStackTrace();
        }

    }

    public static void getCompile() {

        String contractCode;
        String contractHexCode;
        String sourceHexCode;
        String contractHashKey;

        // make compile
        contractHexCode = Compile.getSourceCompile(configure);
        sourceHexCode = Compile.convertSourcecode(configure.dirPath + configure.projectName + "/" + configure.contract);
        contractHashKey = MakeHash.getHashCode(sourceHexCode);
        //contractCode = configure.publicKey+ ":" + configure.taxi +  ":" + contractHexCode + ":" + sourceHexCode;
        contractCode = contractHashKey + ":" + configure.taxi +  ":" + contractHexCode + ":" + sourceHexCode;

        System.out.println("Contract Key [" + ANSI_RED + contractHashKey + ANSI_RESET + "]");

        // send data - make block
        if(isSocketAlive)
            registerContract(contractCode);
    }

    /**
     * Register Contract
     * @param code
     */
    public static void registerContract(String code) {
        sendData("registerContract " + code);
    }

    /**
     * Send Data
     * @param s
     */
    public static void sendData(String s) {
        try {
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(s);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String recvdata = null;
            while((recvdata = reader.readLine()) != null) {
                if(recvdata.length() == 0) break;
                System.out.println("recvd : " + recvdata);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Register Public Key
     * @param inStr
     */
    public static void registerAddress(String[] inStr) {
        if(inStr.length > 1) {
            publicKey = inStr[1];
            System.out.println("Registed Address : " + publicKey);
        } else {
            System.out.println("Ex) registerAddress YOUR_ADDRESS");
        }
    }


}
