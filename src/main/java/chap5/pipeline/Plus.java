package chap5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by hjy on 18-2-22.
 */
public class Plus implements Runnable{

    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<Msg>();

    @Override
    public void run() {
        while (true){
            try {
                Msg msg = bq.take();
                msg.j = msg.i + msg.j;
                Multiply.bq.add(msg);
            }catch (InterruptedException e){

            }
        }
    }
}
