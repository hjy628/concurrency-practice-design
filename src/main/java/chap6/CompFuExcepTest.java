package chap6;

import java.util.concurrent.CompletableFuture;

/**
 * Created by hjy on 18-2-23.
 */
public class CompFuExcepTest {

    public static Integer calc(Integer para){
        return para/0;
    }

    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50)).exceptionally(ex->{
            System.out.println(ex.toString());
            return 0;
        }).thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }


}
