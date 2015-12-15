package com.example.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.example.model.GreetingStatus;

@Service
public class GreetingStatusMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(GreetingStatusMessageHandler.class);

    @JmsListener(destination = "greetingStatus")
    public void processGreeting(GreetingStatus status) {
        logger.info("==============================");
        logger.info("Greeting Status [{}]", status);
        logger.info("==============================");
    }
}
