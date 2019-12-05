package configuration;

import models.Message;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class KieServerConfiguration {


    private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String USER = "wbadmin";
    private static final String PASSWORD = "wbadmin";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private static KieServicesConfiguration conf;
    private static KieServicesClient kieServicesClient;

    public static KieServicesClient initialize() {
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);

        Set<Class<?>> allClasses = new HashSet<Class<?>>();
        allClasses.add(Message.class);
        conf.addExtraClasses(allClasses);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);

        return kieServicesClient;

    }
}
