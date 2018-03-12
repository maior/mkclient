package mkii.mkblock.client;

import java.io.*;
import java.net.Socket;

import static mkii.mkblock.client.common.Constants.ANSI_GREEN;
import static mkii.mkblock.client.common.Constants.ANSI_RED;
import static mkii.mkblock.client.common.Constants.ANSI_RESET;

public class Main {
    public static void main(String[] args) {

        System.out.println("=========== MKII Blockchain Client ===========");
        System.out.println("Usage : exit or quit");

        try {
            // connect localhost
            System.out.print("Connecting mkblock... ");
            Socket socket = new Socket("localhost", 8788);
            System.out.println("[ " + ANSI_GREEN + "OK" + ANSI_RESET + " ]");
            OutputStream output = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.print(">> ");
                String s = br.readLine();
                if(s.equals("quit") || s.equals("exit")) {
                    System.out.println("Good Bye!");
                    break;
                }
                System.out.println("send : " + s);
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(s);
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String recvdata = null;
                while((recvdata = reader.readLine()) != null) {
                    if(recvdata.length() == 0) break;
                    System.out.println("recvd : " + recvdata);
                }

            }
            br.close();
        } catch (IOException ioe) {
            System.out.println("[ " + ANSI_GREEN + "ERROR" + ANSI_RESET + " ]");
            System.out.println(ANSI_RED + "Counld not connect RPC daemon!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Please check your RPC daemon!!!!" + ANSI_RESET);
            //ioe.printStackTrace();
        }

    }
}
