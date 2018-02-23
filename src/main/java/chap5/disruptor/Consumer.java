package chap5.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by hjy on 18-1-31.
 */
public class Consumer implements WorkHandler<PCData>{
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId()+":Event: --"+ pcData.get() + "--");
    }
}
