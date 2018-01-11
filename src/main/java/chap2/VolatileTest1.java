package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class VolatileTest1 {

    static volatile int i = 0;

    public static class PlusTask implements Runnable{
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }

        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(i);

    }



}
