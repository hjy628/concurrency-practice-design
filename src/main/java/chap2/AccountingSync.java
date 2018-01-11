package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class AccountingSync implements Runnable{

    static AccountingSync instance = new AccountingSync();
    static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            synchronized (instance){
                i++;
            }
        }
    }


    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }

}
