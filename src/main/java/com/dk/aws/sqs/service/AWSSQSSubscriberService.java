package com.dk.aws.sqs.service;

import com.dk.aws.sqs.dto.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AWSSQSSubscriberService {

    private final ObjectMapper objectMapper=new ObjectMapper();

    @SqsListener(value = "${sqs.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void handleMessage(String event, @Header("MessageId") String messageId) {

        try {
            log.info("Recieving event {} with messageId {}", event, messageId);
            Message message=objectMapper.readValue(event,Message.class);
            log.info("Message {} recieved ", message);
        } catch (JsonProcessingException e) {
            log.error("Cannot process event {}",event,e);
        }
    }
}
