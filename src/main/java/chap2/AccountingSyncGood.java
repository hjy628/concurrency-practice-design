package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class AccountingSyncGood implements Runnable{

    static int i = 0;


    public static synchronized void increase(){
        i++;
    }



    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }


    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(new AccountingSyncGood());
        Thread t2 = new Thread(new AccountingSyncGood());
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }


}
