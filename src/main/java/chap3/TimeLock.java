package chap3;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 18-1-16.
 */
public class TimeLock implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(3000);
            }else {
                System.out.println("get lock failed");
            }
            System.out.println("get lock success");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

    public static void main(String[] args) {
        TimeLock l1 = new TimeLock();
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l1);
        t1.start();
        t2.start();
    }


}
