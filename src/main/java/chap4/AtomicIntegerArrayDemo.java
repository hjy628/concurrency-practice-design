package chap4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by hjy on 18-1-18.
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                array.getAndIncrement(k%array.length());
            }
        }
    }


    public static void main(String[] args) throws Exception{
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }

        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }

        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }

        System.out.println(array);


    }




}
