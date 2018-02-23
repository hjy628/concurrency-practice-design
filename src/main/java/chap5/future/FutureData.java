package chap5.future;

/**
 * Created by hjy on 18-2-22.
 */
public class FutureData implements Data{
    protected RealData realdata = null;     //FutureData是RealData的包装
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realdata){
        if (isReady){
            return;
        }
        this.realdata = realdata;
        isReady = true;
        notifyAll();    //ReadData已经被注入，通知getResult()
    }

    @Override
    public synchronized String getResult(){ //会等待RealData构造完成
        while (!isReady){
            try {
                wait();     //一直等待，直到RealData被注入
            }catch (InterruptedException e){

            }
        }
        return realdata.result; //由RealData实现
    }


}
