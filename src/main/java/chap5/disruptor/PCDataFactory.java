package chap5.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by hjy on 18-1-31.
 * 产生PCData的工厂类，它会在Disruptor系统初始化时，构造所有的缓冲区中的对象实例(Disruptor会预先分配空间)
 */
public class PCDataFactory implements EventFactory<PCData>{
    public PCData newInstance() {
        return new PCData();
    }
}
