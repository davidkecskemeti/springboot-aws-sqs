package com.dk.aws.sqs.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSConfiguration {

    // Value is populated by the region code.
    @Value("${cloud.aws.region.static}")
    private String region;

    @Autowired(required = false)
    private AWSCredentialsProvider awsCredentialsProvider;

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync(){
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(region)
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(){
        return new QueueMessagingTemplate(amazonSQSAsync());
    }

}
