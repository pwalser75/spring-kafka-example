package ch.frostnova.test.impl;

import ch.frostnova.test.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Kafka messaging service
 *
 * @author wap
 * @since 01.03.2018
 */
@Service
public class KafkaMessagingService implements MessagingService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private List<String> messages = new LinkedList<>();

    private final static String TOPIC = "test";

    @KafkaListener(id = "test-receiver-new",
            topicPartitions = {
                    @TopicPartition(topic = TOPIC, partitions = {"0"})})
    public void listenForNewMessages(String message) {
        log.info("Received new message: " + message);
    }

    @KafkaListener(id = "test-receiver-all",
            topicPartitions = {
                    @TopicPartition(topic = TOPIC,
                            partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0"))})
    public void consumeAllMessages(String message) {
        messages.add(message);
    }

    @Override
    public void send(String message) {
        log.info(String.format("Sending: «%s» to topic «%s»\n", message, TOPIC));
        kafkaTemplate.send(TOPIC, message);
    }

    @Override
    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
