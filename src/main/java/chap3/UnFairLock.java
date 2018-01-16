package chap3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 18-1-16.
 */
public class UnFairLock implements Runnable{

    public static ReentrantLock unFairLock = new ReentrantLock(false);


    @Override
    public void run() {
        while (true){
            try {
                unFairLock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                unFairLock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException{
        UnFairLock r1 = new UnFairLock();
        Thread t1 = new Thread(r1,"Thread_t1");
        Thread t2 = new Thread(r1,"Thread_t2");
        t1.start(); t2.start();

    }


}
