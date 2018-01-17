package chap3;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-17.
 */
public class TraceThreadPoolExecutorDemo {

    public static void main(String[] args) {


        ThreadPoolExecutor pool = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            pool.execute(new DivTask(100,i));
        }


    }


}
