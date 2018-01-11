package chap2;

/**
 * Created by hjy on 18-1-11.
 */
public class DaemonDemo {

    public static class DeamonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        Thread thread = new DeamonT();
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(2000);
    }

}
