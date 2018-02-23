package chap5;

/**
 * Created by hjy on 18-1-31.
 * 生产者-消费者 ：任务
 */
public class PCData {   //任务相关的数据

    private final int intData;  //数据

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data:"+intData;
    }
}
