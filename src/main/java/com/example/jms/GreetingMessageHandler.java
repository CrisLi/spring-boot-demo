package com.example.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.example.model.Greeting;

@Service
public class GreetingMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(GreetingMessageHandler.class);

    @JmsListener(destination = "greetings")
    public void processGreeting(Greeting greeting) {
        logger.info("==============================");
        logger.info("Received Greeting [{}]", greeting);
        logger.info("==============================");
    }
}
