package chap2;

/**
 * Created by hjy on 18-1-11.
 * synchronized用法:
 *  指定加锁对象：对给定对象加锁，进入同步代码块前要获得给定对象的锁
 *  直接作用于实例方法：　相当于对当前实例加锁，进入同步代码块前要获得当前实例的锁
 *  直接作用于静态方法：　相当于对当前类加锁，进入同步代码块前要获得当前类的锁
 */
public class AccountingVol implements Runnable{

    static AccountingVol instance = new AccountingVol();
    static volatile int i =0;
    public static void increase(){
        i++;
    }



    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
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
