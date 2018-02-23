package chap5;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hjy on 18-2-22.
 */
public class SortTest1 {

    static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static int[] arr = {1,2,55,7,2,8,3,93};

    //冒泡排序
    public static void dubble(int[] arr){
        for (int i = arr.length-1 ; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr));
        dubble(arr);
        System.out.println(Arrays.toString(arr));
    }



    public static void oddWvenSort(int[] arr){
        int exchFlag = 1, start = 0;
        while (exchFlag == 1 || start == 1){
            exchFlag = 0;
            for (int i = start; i < arr.length - 1 ; i+=2) {
                if (arr[i]>arr[i+1]){
                    int  temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    exchFlag = 1;
                }
            }

            if (start==0)
                start = 1;
            else
                start = 0;
        }
    }




    static int exchFlag = 1;
    static synchronized int getExchFlag(){
        return exchFlag;
    }
    static synchronized void setExchFlag(int v){
        exchFlag = v;
    }

    public static class OddEvenSortTask implements Runnable{
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i]>arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEvenSort(int[] arr)throws InterruptedException{
        int start = 0;
        while (getExchFlag()==1 || start == 1){
            setExchFlag(0);
            //偶数的数组长度，当start为1时，只有len/2-1个线程
            CountDownLatch latch = new CountDownLatch(arr.length/2-(arr.length%2==0?start:0));
            for (int i = start; i < arr.length-1; i+=2) {
                pool.submit(new OddEvenSortTask(i,latch));
            }
            //等待所有线程结束
            latch.await();
            if (start==0)
                start = 1;
            else start = 0;
        }
    }




}
