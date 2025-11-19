package com.valdir.strconsumer.listers;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {
    @KafkaListener(topics = "str-topic", groupId = "group-0", containerFactory = "strContainerFactory")
    public  void create(String message) {
        log.info("Create::: Received message: {}", message);
    }

    @KafkaListener(topics = "str-topic", groupId = "group-1", containerFactory = "strContainerFactory")
    public  void log(String message) {
        log.info("LOG::: Received message: {}", message);
    }

    @KafkaListener(topics = "str-topic", groupId = "group-2", containerFactory = "strContainerFactory")
    public  void history(String message) {
        log.info("HISTORY::: Received message: {}", message);
    }
}
