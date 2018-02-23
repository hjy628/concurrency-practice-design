package chap5.disruptor;

/**
 * Created by hjy on 18-1-31.
 */
public class PCData {

    private long value;

    public void set(long value){
        this.value = value;
    }


    public long get(){
        return value;
    }

}
