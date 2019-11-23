package configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaListener {


    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "SampleConsumer";

    public static Properties properties() {

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CLIENT_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return props;

    }
    public void startConsummer() {

        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties());

        while (true) {
            kafkaConsumer.subscribe(Collections.singletonList("ternium"));
            ConsumerRecords<Long, String> consumerRecords =
                    kafkaConsumer.poll(1000);

            for (ConsumerRecord<Long, String> record : consumerRecords) {

                System.out.println("Contenido de mensaje" + record.value());
                    //Aca ingresa lo que desea hacer con el contenido de los mensajes,
                    // record.key() devuelve la key del mensaje
                    //record.value() devuelve el contenido  del mensaje
            }
        }
    }
}
