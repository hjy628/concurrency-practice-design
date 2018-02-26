package chap7;

import akka.actor.UntypedActor;

/**
 * Created by hjy on 18-2-24.
 */
public class Greeter extends UntypedActor{

    public static enum Msg{
        GREET,DONE
    }


    public void onReceive(Object o) throws Exception {
        if (o==Msg.GREET){
            System.out.println("Hello world");
            getSender().tell(Msg.DONE,getSelf());
        }else
            unhandled(o);
    }
}
