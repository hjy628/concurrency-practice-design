package chap7;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 18-2-24.
 */
public class Supervisor extends UntypedActor{

    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES), new Function<Throwable, SupervisorStrategy.Directive>() {
        @Override
        public SupervisorStrategy.Directive apply(Throwable throwable) throws Exception {
            if (throwable instanceof ArithmeticException){
                System.out.println("meet ArithmeticException,just resume");
                return SupervisorStrategy.resume();
            }else if (throwable instanceof NullPointerException){
                System.out.println("meet NullPointerException,restart");
                return SupervisorStrategy.restart();
            }else if (throwable instanceof IllegalArgumentException){
                return SupervisorStrategy.stop();
            }else {
                return SupervisorStrategy.escalate();
            }
        }
    });


    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Props){
            getContext().actorOf((Props) o,"restartActor");
        }else {
            unhandled(o);
        }
    }
}
