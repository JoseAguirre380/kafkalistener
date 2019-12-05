package rules;


import java.util.Arrays;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.Message;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;

import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.server.api.model.KieServiceResponse;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.RuleServicesClient;

import static configuration.KieServerConfiguration.initialize;

public class RulesExecutor {


    public static Integer executeRules(String jsonPayload) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message message = objectMapper.readValue(jsonPayload, Message.class);

            String containerId = "ternium_1.0.0-SNAPSHOT";
            System.out.println("== Sending commands to the server ==");
            RuleServicesClient rulesClient = initialize().getServicesClient(RuleServicesClient.class);

            KieCommands commandsFactory = KieServices.Factory.get().getCommands();

            GetObjectsCommand getObjectsCommand = new GetObjectsCommand();
            getObjectsCommand.setOutIdentifier("objects");

            Command<?> insert = commandsFactory.newInsert(message,"objectIdetifier");
            //Command<?> agendaGroup = commandsFactory.newAgendaGroupSetFocus("EstadoViaje");
            Command<?> fireAllRules = commandsFactory.newFireAllRules();
            Command<?> getObjects = commandsFactory.newGetObjects("objects");


            Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, getObjects, fireAllRules));

            ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(containerId, batchCommand);



            if (executeResponse.getType() == KieServiceResponse.ResponseType.SUCCESS) {
                System.out.println("Commands executed with success! Response: ");


                Message responseMessage = (Message)executeResponse.getResult().getValue("objectIdetifier");

                System.out.println(("respuesta de objeto:" + responseMessage.toString()));

                String AgendaGroupNname = "";
                if (responseMessage.getData().containsKey("AgendaGroupName")){
                    //Aqui recibira el nombre de la regla que quiere ejecutar
                    AgendaGroupNname = responseMessage.getData().get("AgendaGroupName").toString();
                }
                System.out.println(AgendaGroupNname);

                // Message messageResponse = (Message)executeResponse.getResult().getValue("message");
                return 1;
            } else {
                System.out.println("Error executing rules. Message: ");
                System.out.println(executeResponse.getMsg());
                return 0;
            }

        } catch (Exception e) {
            System.out.println("Error executing rules. Message: " + e.getMessage());
        }
        return 1;
    }
}

