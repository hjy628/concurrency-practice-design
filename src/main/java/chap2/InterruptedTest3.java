package chap2;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-11.
 * Thread.sleep()方法由于中断而抛出异常，此时，它会清除中断标记，如果不处理，那么在下一次循环开始时，就无法捕获这个中断，故在异常处理中，
 * 再次设置中断标记位
 */
public class InterruptedTest3 {

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted!");
                        break;
                    }

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException e){
                        System.out.println("Interrupted When sleep");
                        //设置中断状态
                        Thread.currentThread().interrupt();
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
