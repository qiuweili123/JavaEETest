package com.ms.rest.client;


import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.dmn.DMNModelInfoList;
import org.kie.server.client.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/31.
 */
public class RestClientTest {

    private static final String URL = "http://localhost:9090/kie-server/services/rest/server";
    private static final String USER = "kieserver";
    private static final String PASSWORD = "kieserver1!";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private static KieServicesConfiguration conf;
    private static KieServicesClient kieServicesClient;

    public static void main(String[] args) {
        initialize();
        // kieServicesClient.getReleaseId("com.test:driving:1.0");
        System.out.println("##client==" + kieServicesClient.getServerInfo());
        // listContaners();
        //disposeAndCreateContainer();
        // executeCommands();
          executeCommandWithResult();
        //getRelaseId();
        // getDMNclient();
    }


    public static void initialize() {
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setMarshallingFormat(MarshallingFormat.JSON);
        conf.setTimeout(3000);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);


    }

    public static void listContaners() {

        KieContainerResourceList containersList = kieServicesClient.listContainers().getResult();
        List<KieContainerResource> kieContainers = containersList.getContainers();
        System.out.println("Available containers: ");
        for (KieContainerResource container : kieContainers) {

            System.out.println("##" + container.getContainerId() + " (" + container.getReleaseId() + ")");

        }
    }

    public static void disposeAndCreateContainer() {
        System.out.println("== Disposing and creating containers ==");
        List<KieContainerResource> kieContainers = kieServicesClient.listContainers().getResult().getContainers();
        if (kieContainers.size() == 0) {
            System.out.println("No containers available...");
            return;
        }
        KieContainerResource container = kieContainers.get(0);
        String containerId = container.getContainerId();
        ServiceResponse<Void> responseDispose = kieServicesClient.disposeContainer(containerId);
        if (responseDispose.getType() == ServiceResponse.ResponseType.FAILURE) {
            System.out.println("Error disposing " + containerId + ". Message: ");
            System.out.println(responseDispose.getMsg());
            return;
        }
        System.out.println("Success Disposing container " + containerId);
        System.out.println("Trying to recreate the container...");
        ServiceResponse<KieContainerResource> createResponse = kieServicesClient.createContainer(containerId, container);
        if (createResponse.getType() == ServiceResponse.ResponseType.FAILURE) {
            System.out.println("Error creating " + containerId + ". Message: ");
            System.out.println(responseDispose.getMsg());
            return;
        }
        System.out.println("Container recreated with success!");
    }


    public static void executeCommands() {
        System.out.println("== Sending commands to the server ==");
        RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

        KieCommands commandsFactory = KieServices.Factory.get().getCommands();
        Command<?> insert = commandsFactory.newInsert("Some String OBJ");
        Command<?> fireAllRules = commandsFactory.newFireAllRules();
        Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, fireAllRules));
        ServiceResponse<String> executeResponse = rulesClient.executeCommands("hello", batchCommand);
        if (executeResponse.getType() == ServiceResponse.ResponseType.SUCCESS) {
            System.out.println("Commands executed with success! Response: ");
            System.out.println(executeResponse.getResult());
        } else {
            System.out.println("Error executing rules. Message: ");
            System.out.println(executeResponse.getMsg());
        }
    }

    /**
     * RuleServicesClient 用于向远程服务器
     */
    public static void executeCommandWithResult() {

    /*    Applicant applicant = new Applicant();
        applicant.setAge(27);
        applicant.setSex(2);
        applicant.setId(1L);

        Recipe recipe = new Recipe();
        recipe.setMedicineId(1L);
        recipe.setApplicantId(1L);
        recipe.setUseNum(new BigDecimal("2.0"));
*/
        RuleServicesClient rules = kieServicesClient.getServicesClient(RuleServicesClient.class);

        KieCommands cmdFactory = KieServices.Factory.get().getCommands();

        List<Command<?>> commands = new LinkedList<Command<?>>();
        //当申请人实例插入到引擎中时，将根据规则的约束进行评估,可以理解参数传递
      /*  commands.add(cmdFactory.newInsert(applicant, "applicant"));
        commands.add(cmdFactory.newInsert(recipe, "recipe"));*/
        //多个实例必须生成不同的实例名称 applicant1
    /*    applicant.setAge(57);
        commands.add(cmdFactory.newInsert(applicant, "applicant1"));*/

// 匹配规则
        commands.add(cmdFactory.newFireAllRules());
        ServiceResponse<org.kie.api.runtime.ExecutionResults> response = rules.executeCommandsWithResults("driving_1.0.0", cmdFactory.newBatchExecution(commands));  // applySession第一个参数，容器名称，第二个参数为回话标示，可以不填, 默认为defaultKieSession"

        System.out.println(response.getMsg());
        ExecutionResults result = response.getResult(); //获取请求
        ServiceResponse.ResponseType type = response.getType();  //请求状态
        System.out.println(type.name());
       /* applicant = (Applicant) result.getValue("applicant"); //和web 获取前端传值很像吧
        System.out.println("result====" + applicant.getValid());*/
    }

    public static void getRelaseId() {
        ServiceResponse<ReleaseId> releaseId = kieServicesClient.getReleaseId("driving:1.1");
        System.out.println("realaseInfo###" + releaseId.getType());
    }

    public static void getDMNclient() {
        DMNServicesClient client = kieServicesClient.getServicesClient(DMNServicesClient.class);
        ServiceResponse<DMNModelInfoList> models = client.getModels("user");
        System.out.println(models.getResult());
    }

    public static void testQurey() {

        /*  commands.add(cmdFactory.newInsert(applicant, "applicant1"));*//*
        QueryCommand queryCommand = new QueryCommand();
        queryCommand.setName("person");
        commands.add(queryCommand);*/

    }
}
