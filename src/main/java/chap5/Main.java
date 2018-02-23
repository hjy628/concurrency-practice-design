package chap5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hjy on 18-1-31.
 * 生产者-消费者： 调用生产者及消费者客户端
 */
public class Main {

    public static void main(String[] args) throws Exception{
        //建立缓冲区
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>(10);

        Producer producer1 = new Producer(queue);   //建立生产者
        Producer producer2 = new Producer(queue);   //建立生产者
        Producer producer3 = new Producer(queue);   //建立生产者
        Consumer consumer1 = new Consumer(queue);   //建立消费者
        Consumer consumer2 = new Consumer(queue);   //建立消费者
        Consumer consumer3 = new Consumer(queue);   //建立消费者

        ExecutorService service = Executors.newCachedThreadPool();  //建立线程池
        service.execute(producer1); //运行生产者
        service.execute(producer2);
        service.execute(producer3);

        service.execute(consumer1); //运行消费者
        service.execute(consumer2);
        service.execute(consumer3);

        Thread.sleep(10*1000);

        producer1.stop();   //停止生产者
        producer2.stop();
        producer3.stop();


        Thread.sleep(3000);


        service.shutdown();
    }



}
