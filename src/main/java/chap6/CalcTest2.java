package chap6;

import java.util.concurrent.CompletableFuture;

/**
 * Created by hjy on 18-2-23.
 */
public class CalcTest2 {
    public static Integer calc(Integer para){
        try {
            //模拟一个长时间的执行
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        return para*para;
    }

    public static void main(String[] args) throws Exception{

        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50))
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);

        fu.get();

    }


}
