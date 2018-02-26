package chap7;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by hjy on 18-2-24.
 */
public class HelloWorld extends UntypedActor{
    ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class),"greeter");
        System.out.println("Greeter Actor Path:"+greeter.path());
        greeter.tell(Greeter.Msg.GREET,getSelf());
    }

    public void onReceive(Object o) throws Exception {
        if (o==Greeter.Msg.DONE){
            greeter.tell(Greeter.Msg.GREET,getSelf());
            getContext().stop(getSelf());
        }else
            unhandled(o);
    }
}
