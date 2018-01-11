package chap2;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-11.
 */
public class InterruptedTest2 {

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted!");
                        break;
                    }
                    Thread.yield();
                }
            }
        };

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt(); //中断有效

    }

}
