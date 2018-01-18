package chap4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hjy on 18-1-18.
 *
 * 为账户余额小于20元的客户一次性赠送20元，，但每位客户只能被赠送一次
 */
public class AtomicReferenceDemo {

    static AtomicReference<Integer> money = new AtomicReference<Integer>();
    //设置账户初始值小于20,显然这是一个需要被充值的账户


    public static void main(String[] args) {

        money.set(19);

        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        while (true){
                            Integer m = money.get();
                            if (m<20){
                                if (money.compareAndSet(m,m+20)){
                                    System.out.println("余额小于20元，充值成功，余额:"+money.get()+"元");
                                    break;
                                }
                            }else {
                                System.out.println("余额大于20元，无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }



        //用户消费线程，模拟消费行为
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true){
                        Integer m = money.get();
                        if (m>10){
                            System.out.println("大于10元");
                            if (money.compareAndSet(m,m-10)){
                                System.out.println("成功消费10元，余额:"+money.get());
                                break;
                            }
                        }else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {Thread.sleep(1000);}catch (InterruptedException e){}
                }

            }
        }.start();



    }


}
