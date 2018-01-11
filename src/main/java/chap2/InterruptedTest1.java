package chap2;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-11.
 */
public class InterruptedTest1 {

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    Thread.yield();
                }
            }
        };

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt(); //虽然对t1进行了中断，但是t1中没有中断处理的逻辑。所以设上中断状态不会发生任何作用

    }

}
