package chap5.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by hjy on 18-2-22.
 */
public class FutureMain {

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        //构造FutureTask
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //执行FutureTask,相当于上例中的client.request("a")发送请求
        //在这里开启线程进行RealData的call()执行
        executor.submit(future);

        System.out.println("请求完毕");

        try {
            //这里依然可以做额外的数据操作
            System.out.println("其他业务逻辑处理1");
            System.out.println("其他业务逻辑处理2");
            System.out.println("其他业务逻辑处理3");

            Thread.sleep(1000);
        }catch (InterruptedException e){

        }

        while (true){
            if (future.isDone()){
                System.out.println("数据="+future.get());
                executor.shutdown();
                break;
            }
        }


    }

}
