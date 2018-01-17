package chap3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-17.
 */
public class DivTask implements Runnable{
    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a/b;
        System.out.println(re);
    }

    public static void main(String[] args) throws InterruptedException,ExecutionException{

        ThreadPoolExecutor pool = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            pool.submit(new DivTask(100,i));
        }
    }
}
