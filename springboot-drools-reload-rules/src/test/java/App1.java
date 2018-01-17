import org.drools.core.command.impl.ExecutableCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App1 {

    public static void main(String[] args) {
        /*HelloWorld p1 = new HelloWorld();
        p1.setMessage("Joe");*/

        String url = "http://localhost:9090/kie-server/services/rest/server";
        String username = "kieserver";
        String password = "kieserver1!";
        String container = "HelloWorld_1.0.22";
        String session = "defaultKieSession";

        KieServicesConfiguration config = KieServicesFactory
                .newRestConfiguration(url,
                        username,
                        password);
        Set<Class<?>> allClasses = new HashSet<Class<?>>();
        //allClasses.add(HelloWorld.class);
        config.addJaxbClasses(allClasses);

        KieServicesClient client = KieServicesFactory.newKieServicesClient(config);
        RuleServicesClient ruleClient = client.getServicesClient(RuleServicesClient.class);

//
        final List<ExecutableCommand> commands = new ArrayList<ExecutableCommand>();

        commands.add(new FireAllRulesCommand());
        //  commands.add(new InsertObjectCommand(p1));

        BatchExecutionCommand batchCommand = KieServices.Factory
                .get().getCommands().newBatchExecution(commands,
                        session);

        ServiceResponse<String> response = ruleClient.executeCommands(container,
                batchCommand);
        System.out.println(response.getResult());
    }
}