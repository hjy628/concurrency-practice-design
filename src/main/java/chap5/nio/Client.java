package chap5.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by hjy on 18-2-22.
 */
public class Client {

    public static void main(String[] args) throws Exception{

        for (int i = 0; i < 100; i++) {
            echoTest();
        }


    }

    public static void echoTest() throws Exception{

        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost",8000));
            writer = new PrintWriter(client.getOutputStream(),true);
            writer.println("Hello!");
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server: "+reader.readLine());
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.close();
            }
            if (reader!=null){
                reader.close();
            }
            if (client!=null){
                client.close();
            }
        }

    }

}
