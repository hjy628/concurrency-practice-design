package chap6;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by hjy on 18-2-23.
 */
public class Point {

    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX,double deltaY){ //这是一个排它锁
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){    //只读方法
        long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)){
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            }finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX + currentY*currentY);
    }

}
