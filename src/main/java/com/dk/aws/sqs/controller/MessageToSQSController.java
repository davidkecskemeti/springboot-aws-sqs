package com.dk.aws.sqs.controller;

import com.dk.aws.sqs.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageToSQSController {

    @Value(value = "${sqs.name}")
    private String SQSName;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void sendMessageToSQS(@RequestBody @Validated Message message){
        log.info("Sending message {} to SQS",message);
        queueMessagingTemplate.convertAndSend(SQSName,message);
        log.info("Message {} sent successfully",message);
    }
}