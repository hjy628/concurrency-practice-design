package chap3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 18-1-16.
 */
public class IntLock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /*
    *   控制加锁顺序，方便构造死锁
    *
    */
    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock==1){
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){

                }
                lock2.lockInterruptibly();
                System.out.println("1");

            }else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){

                }
                lock1.lockInterruptibly();
                System.out.println("2");

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if (lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+":线程退出!");
        }
    }


    public static void main(String[] args) throws InterruptedException{
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();t2.start();
        Thread.sleep(1000);
        //中断其中一个线程
        t2.interrupt();
    }

}
