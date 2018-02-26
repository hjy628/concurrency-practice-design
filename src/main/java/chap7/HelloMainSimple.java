package chap7;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Created by hjy on 18-2-24.
 */
public class HelloMainSimple {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class),"helloworld");
        System.out.println("HelloWorld Actor Path:"+a.path());
    }

}
