package com.example.jms;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageSender<T extends Serializable> {

    public static final String MESSAGE_TYPE = "messageType";

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void send(String destination, String messageType, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).setHeader(MESSAGE_TYPE, messageType).build();
        jmsTemplate.send(destination, message);
    }
}
