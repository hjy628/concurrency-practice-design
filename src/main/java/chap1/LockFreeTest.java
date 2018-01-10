package chap1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hjy on 18-1-10.
 */
public class LockFreeTest {

    public static void main(String[] args) {
        AtomicInteger atomicVar = new AtomicInteger(1);
        int localVar = 0;

        //无锁的代码示例
        while (!atomicVar.compareAndSet(localVar,localVar+1)){
            localVar = atomicVar.get();
        }


    }

}
