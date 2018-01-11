package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;


    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new ReaderThread().start();

        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);


    }


}
