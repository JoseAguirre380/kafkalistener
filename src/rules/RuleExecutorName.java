package rules;

import configuration.KieServerConfiguration;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieServiceResponse;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.FORMAT;
import static org.kie.internal.command.CommandFactory.newGetObjects;

public class RuleExecutorName {

    private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String USER = "wbadmin";
    private static final String PASSWORD = "wbadmin";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private static KieServicesConfiguration conf;
    private static KieServicesClient kieServicesClient;

    public static Integer executeRules(String jsonPayload) {

        String containerId = "ternium_1.0.0-SNAPSHOT";
        System.out.println("== Sending commands to the server ==");
        RuleServicesClient rulesClient = initialize().getServicesClient(RuleServicesClient.class);

        KieCommands commandsFactory = KieServices.Factory.get().getCommands();

        GetObjectsCommand getObjectsCommand = new GetObjectsCommand();
        getObjectsCommand.setOutIdentifier("message");

        Command<?> insert = commandsFactory.newInsert(jsonPayload);

        //recibe como parametro el nombre de Agenda Group Asignado en la regla
        Command<?> agendaGroup = commandsFactory.newAgendaGroupSetFocus("ruleName");
        Command<?> fireAllRules = commandsFactory.newFireAllRules();
        Command<?> getObjects = commandsFactory.newGetObjects("message");

        Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert,agendaGroup, getObjects,fireAllRules));

        ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(containerId, batchCommand);

        if(executeResponse.getType() == KieServiceResponse.ResponseType.SUCCESS) {
            System.out.println("Commands executed with success! Response: ");
            System.out.println(executeResponse.getResult());
            System.out.println(("respuesta de objeto:" + executeResponse.getResult().getValue("message").toString()));
            return 1;
        } else {
            System.out.println("Error executing rules. Message: ");
            System.out.println(executeResponse.getMsg());
            return 0;
        }

    }
    public static KieServicesClient initialize() {
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);


        conf.setMarshallingFormat(FORMAT);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);

        return kieServicesClient;

    }
}
