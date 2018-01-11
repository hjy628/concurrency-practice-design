package chap2;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-11.
 */
public class BadSuspend {

    public static Object u = new Object();

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                Thread.currentThread().suspend();
            }
        }
    }


    public static void main(String[] args) throws Exception{
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }


}
