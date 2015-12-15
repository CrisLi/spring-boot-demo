package com.example.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.example.model.Greeting;
import com.example.model.GreetingStatus;

@Service
public class GreetingMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(GreetingMessageHandler.class);

    @JmsListener(destination = "greetings")
    @SendTo("greetingStatus")
    public GreetingStatus processGreeting(@Header String messageType, Greeting greeting) {
        logger.info("==============================");
        logger.info("Received Greeting [{}] Greeting Type {}", greeting, messageType);
        logger.info("==============================");

        return new GreetingStatus("Greeting Received");
    }
}
