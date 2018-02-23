package chap5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by hjy on 18-2-22.
 */
public class Div implements Runnable{

    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<Msg>();


    @Override
    public void run() {
        while (true){
            try {
                Msg msg = bq.take();
                msg.i = msg.i /2;
                System.out.println(msg.orgStr + "=" + msg.i);
            }catch (InterruptedException e){

            }
        }
    }
}
