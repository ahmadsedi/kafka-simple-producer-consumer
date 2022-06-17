package com.ahmadsedighi.kafka.simple.producer;

import com.ahmadsedighi.kafka.simple.Constants;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Map;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 17/06/2022
 * Time: 11:37
 */

public class BasicProducer {
    public static void main(String[] args) throws InterruptedException {
        final var topic = Constants.TOPIC_NAME;
        final Map<String, Object> config = Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092", ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 127000);
        try (var producer = new KafkaProducer<String, String>(config)) {
            while (true) {
                final var key = "myKey";
                final var value = new Date().toString();
                System.out.format("Publishing record with value %s%n", value);
                final Callback callback = (metadata, exception) -> {
                    System.out.format("Published with metadata: %s, error: %s%n",
                            metadata, exception);
                };
                // publish the record, handling the metadata in the callback
                producer.send(new ProducerRecord<>(topic, key, value), callback);
                // wait a second before publishing another
                Thread.sleep(1000);
            }
        }
    }
}
