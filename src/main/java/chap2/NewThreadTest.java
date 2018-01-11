package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class NewThreadTest {

    public static void main(String[] args) {

        Thread t1 = new Thread();
        t1.start();     //执行t1的run方法，新建线程

        Thread t2 = new Thread();
        t2.run();   //能通过编译也能正常执行，不过只会在当前线程中串行执行run()中的代码，不会开启新线程






    }


}
