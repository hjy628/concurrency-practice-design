package chap6;

import java.util.concurrent.CompletableFuture;

/**
 * Created by hjy on 18-2-23.
 */
public class AskThreadTest {

    public static class AskThread implements Runnable{
        CompletableFuture<Integer> re = null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                myRe = re.get() * re.get();
            }catch (Exception e){

            }
            System.out.println(myRe);
        }
    }


    public static void main(String[] args) throws Exception{
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        //模拟长时间的计算过程
        Thread.sleep(1000);
        //告知完成结果
        future.complete(60);
    }
}
