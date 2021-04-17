package com.dk.aws.sqs.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private Integer id;
    private String message;
}
