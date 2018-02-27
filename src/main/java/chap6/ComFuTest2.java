package chap6;

import java.util.concurrent.CompletableFuture;

/**
 * Created by hjy on 18-2-23.
 */
public class ComFuTest2 {

    public static Integer calc(Integer para){
        return para / 2;
    }

    public static void main(String[] args) throws Exception{
      /*  CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50))
                .thenCompose((i)->CompletableFuture.supplyAsync(()->calc(i)))
                .thenApply((str)->"\""+str+"\"").thenAccept(System.out::println);
        fu.get();*/

        CompletableFuture<Integer> intFuture1 = CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(()->calc(25));

        CompletableFuture<Void> fu = intFuture1.thenCombine(intFuture2,(i,j)->(i+j))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();

    }


}
