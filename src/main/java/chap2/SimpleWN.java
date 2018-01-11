package chap2;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-1-11.
 * 1515674350740:T1 start!
 1515674350740:T1 wait for object
 1515674350740:T2 start! notify one thread
 1515674350740:T2 end!
 1515674352741:T1 end!

 Object.wait()和Thread.Sleep()方法都可以让线程等待若干时间，除了wait()可以被唤醒外，另一个主要区别是wait()方法会释放目标对象的锁
 而Thread.sleep()方法不会释放任何资源



 */
public class SimpleWN {

    final static Object object = new Object();


    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }

    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T1 start! ");
                try {
                    System.out.println(System.currentTimeMillis()+":T1 wait for object");
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end!");
            }
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one thread ");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end!");
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


}
