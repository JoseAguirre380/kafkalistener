import configuration.KafkaConfiguration;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import rules.RulesExecutor;

import java.util.Collections;

public class FreightShipment {

    KafkaConsumer kafkaConsumer;

    public FreightShipment() {

    }
    public void doWork() {
        kafkaConsumer.commitAsync();
    }

    public Integer startConsummer(String topicName, String kafkaKey, String jsonPayload){

        kafkaConsumer = new KafkaConsumer(KafkaConfiguration.properties());
        int i = 0;
        while(true) {
        kafkaConsumer.subscribe(Collections.singletonList(topicName));
        ConsumerRecords<Long, String> consumerRecords =
                kafkaConsumer.poll(1000);
            System.out.println("Consumiendo kafka:" + i);
            i++;
            for (ConsumerRecord<Long, String> record : consumerRecords) {
               // System.out.println("Mensaje enocntrado: " + record.key() +record.value());
                //if (record.key() != null && record.key().toString().equals(kafkaKey)) {
                //    System.out.println("Mensaje encontrado con key correcto: " + record.value());
                    //Al encontrar el mensaje con la key indicada cierra la conexion de kafk
                    this.kafkaConsumer.close();

                    //Devuelve el valor recibido por parte de kie server
                    return RulesExecutor.executeRules(record.value());
                //}
            }
        }

    }

}
