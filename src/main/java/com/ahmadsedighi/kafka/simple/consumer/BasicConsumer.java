package com.ahmadsedighi.kafka.simple.consumer;

import com.ahmadsedighi.kafka.simple.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 17/06/2022
 * Time: 13:44
 */

public class BasicConsumer {
    public static void main(String[] args) {
        final var topic = Constants.TOPIC_NAME;
        final Map<String, Object> config = Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                ConsumerConfig.GROUP_ID_CONFIG, "basic-consumer-sample",
//                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        try (var consumer = new KafkaConsumer<String, String>(config)) {
            consumer.subscribe(Set.of(topic));
            while (true) {
                final var records = consumer.poll(Duration.ofMillis(127000));
                for (var record : records) {
                    System.out.format("Got record with value %s%n", record.value());
                }
                consumer.commitAsync();
            }
        }

    }
}
