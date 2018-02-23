package chap5.future;

/**
 * Created by hjy on 18-2-22.
 */
public class Client {

    public Data request(final String queryStr){
        final FutureData future = new FutureData();

        new Thread(){
            @Override
            public void run() {
                //RealData的构造很慢,所以在单独的线程中进行
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;      //FutureData会被立即返回
    }


}
