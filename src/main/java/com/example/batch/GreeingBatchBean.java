package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.service.GreetingService;

@Component
@Profile("batch")
public class GreeingBatchBean {

    private static final Logger logger = LoggerFactory.getLogger(GreeingBatchBean.class);

    @Autowired
    private GreetingService greetingService;

    @Scheduled(initialDelayString = "${batch.greeting.initialDelay}", fixedDelayString = "${batch.greeting.fixedDelay}")
    public void scheduleJob() {
        logger.info("> job");
        greetingService.findAll().forEach(g -> logger.info(g.toString()));
        logger.info("< job");
    }
}
