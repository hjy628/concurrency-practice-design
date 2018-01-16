package chap3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by hjy on 18-1-16.
 */
public class SemapDemo implements Runnable{

    final Semaphore semp = new Semaphore(5);


    @Override
    public void run() {
        try {
            semp.acquire();
            //模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
            semp.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(demo);
        }
    }


}
