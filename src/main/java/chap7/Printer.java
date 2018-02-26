package chap7;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by hjy on 18-2-24.
 */
public class Printer extends UntypedActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Integer){
            System.out.println("Printer:"+msg);
        }
        if (msg== AMyWorker.Msg.DONE){
            log.info("Stop working");
        }if (msg== AMyWorker.Msg.CLOSE){
            log.info("I will shutdown");
            getSender().tell(AMyWorker.Msg.CLOSE,getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(msg);
        }
    }
}
