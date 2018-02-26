package chap7;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 18-2-24.
 */
public class RWatchActor extends UntypedActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
    public Router router;

    {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef worker = getContext().actorOf(Props.create(MyWorker.class),"worker_"+i);
            getContext().watch(worker);
            routees.add(new ActorRefRoutee(worker));
        }
       // router = new Router(new RoundRobinRoutingLogic(),routees);
       // router = new Router(new BroadcastRoutingLogic(),routees);
       // router = new Router(new RandomRoutingLogic(),routees);
        router = new Router(new SmallestMailboxRoutingLogic(),routees);
    }



    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof MyWorker.Msg){
            router.route(msg,getSender());
        }else if (msg instanceof Terminated){
            router = router.removeRoutee(((Terminated)msg).actor());
            System.out.println(((Terminated)msg).actor().path()+"  is closed,routees="+router.routees().size());
            if (router.routees().size()==0){
                System.out.println("Close system");

                getContext().system().shutdown();
            }
        }else {
            unhandled(msg);
        }
    }
}
