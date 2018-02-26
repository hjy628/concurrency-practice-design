package chap7;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * Created by hjy on 18-2-24.
 */
public class CompanyActor extends UntypedActor{
    private Ref.View<Integer> count = STM.newRef(100);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Coordinated){
            final Coordinated c = (Coordinated)msg;
            final int downCount = (Integer)c.getMessage();
            STMDemo.employee.tell(c.coordinate(downCount),getSelf());

            try {
                c.atomic(new Runnable() {
                    @Override
                    public void run() {
                        if (count.get()<downCount){
                            throw new RuntimeException("less than "+downCount);
                        }
                        STM.increment(count,-downCount);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if ("GetCount".equals(msg)){
            getSender().tell(count.get(),getSelf());
        }else {
            unhandled(msg);
        }



    }
}
