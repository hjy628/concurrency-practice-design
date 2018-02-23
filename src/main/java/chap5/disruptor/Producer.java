package chap5.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by hjy on 18-1-31.
 * 生产者需要一个RingBuffer的引用，也就是环形缓冲区，他有一个重要的方法pushData()将产生的数据推入缓冲区
 * 方法pushData()接收一个ByteBuffer对象
 * 在ByteBuffer中可以用来包装任何数据类型
 * 这里用来存储long整数，pushData()的功能就是将传入的ByteBuffer中的数据提取出来，并装载到环形缓冲区中
 * next方法得到下一个可用的序号，通过序列号取得下一个空闲可用的PCData,并将PCData的数据设置为期望值，这个值最终会传递给消费者,
 * 最后进行数据发布，只有发布后的数据才会真正被消费者看见
 */
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void pushData(ByteBuffer bb){

        long sequence = ringBuffer.next();  //Grab the next sequence
        try {
            PCData data = ringBuffer.get(sequence);     //get the entry in the disruptor for the sequence
            data.set(bb.getLong(0));    //fill in data
        }finally {
            ringBuffer.publish(sequence);
        }

    }


}
