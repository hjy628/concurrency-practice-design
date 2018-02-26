package chap7;

import akka.actor.Inbox;
import akka.actor.UntypedActor;
import akka.dispatch.Mapper;
import scala.concurrent.Future;

/**
 * Created by hjy on 18-2-24.
 */
public class CounterActor extends UntypedActor{
    Mapper addMapper = new Mapper<Integer,Integer>(){
        @Override
        public Integer apply(Integer parameter) {
            return parameter+1;
        }
    };


    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Integer){
            for (int i = 0; i < 10000; i++) {
                //我希望能够知道future何时结束
                Future<Integer> f = AgentDemo.countAgent.alter(addMapper);
                AgentDemo.futures.add(f);
            }
            getContext().stop(getSelf());
        }else
            unhandled(msg);
    }
}
