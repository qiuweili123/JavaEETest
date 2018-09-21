package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaTest {


    public static void main(String[] args) {
        final    ActorSystem system = ActorSystem.create("helloakka");

        //创建actor
        final   ActorRef printerActor  = system.actorOf(Printer.props(), "printerActor");
        final ActorRef howdyGreeter = system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
        //howdyGreeter.tell(new WhoToGreet("Akka"), ActorRef.noSender());
        howdyGreeter.tell(new Greeter.Greet(), ActorRef.noSender());
       // System.out.println(">>> Press ENTER to exit <<<");
    }

}
