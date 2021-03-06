package chap7;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by hjy on 18-2-24.
 */
public class AMyWorker extends UntypedActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);

    public static enum Msg{
        WORKING,DONE,CLOSE;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stoping");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Integer){
            int i = (Integer)msg;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
            getSender().tell(i*i,getSelf());
        }
        if (msg== Msg.DONE){
            System.out.println("stop working");
        }
        if (msg== Msg.CLOSE){
            System.out.println("i will shutdown");
            getSender().tell(Msg.CLOSE,getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(msg);
        }
    }
}
