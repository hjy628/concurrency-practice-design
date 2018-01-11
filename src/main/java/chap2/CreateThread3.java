package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class CreateThread3 implements Runnable{

    public static void main(String[] args) {

        Thread t1 = new Thread(new CreateThread3());
        t1.start();

    }

    @Override
    public void run() {
        System.out.println("hello,i am Runnable");
    }
}
