package chap5.disruptor;

/**
 * Created by hjy on 18-2-22.
 * 适用jdk7版本，jdk8版本已为cpu伪共享问题做了优化，此种填充方案失效
 */
public final class FalseSharing implements Runnable{
    public final static int NUM_THREADS = 4;   //change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws Exception{
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration="+(System.currentTimeMillis()-start));
    }


    private static void runTest() throws InterruptedException{
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t :
                threads) {
            t.start();
        }

        for (Thread t :
                threads) {
            t.join();
        }

    }

    @Override
    public void run() {

        long i = ITERATIONS + 1;
        while (0!= --i){
            longs[arrayIndex].value=i;
        }

    }




    public final static class VolatileLong{
        public volatile long value = 0L;
        public long p1,p2,p3,p4,p5,p6,p7;   //comment out
    }
}
