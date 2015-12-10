package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.model.Greeting;

@Service
public class EmailServiceBean implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceBean.class);

    @Override
    public boolean send(Greeting greeting) {
        logger.info("> send");
        boolean result = sendEmail(greeting);
        logger.info("< send");
        return result;
    }

    @Async
    @Override
    public void sendAsync(Greeting greeting) {
        logger.info("> sendAsync");
        this.sendEmail(greeting);
        logger.info("< sendAsync");
    }

    @Async
    @Override
    public Future<Boolean> sendAsyncWithResult(Greeting greeting) {
        logger.info("> sendAsyncWithResult");

        CompletableFuture<Boolean> response = new CompletableFuture<>();

        try {
            boolean result = this.sendEmail(greeting);
            response.complete(result);
        } catch (Exception e) {
            logger.error("Send email error", e);
            response.completeExceptionally(e);
        }

        logger.info("< sendAsyncWithResult");
        return response;
    }

    private boolean sendEmail(Greeting greeting) {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
            }
            logger.info("[{}] email sent", greeting.getText());
        } catch (InterruptedException e) {
            logger.error("Send email error", e);
            return false;
        }
        return true;
    }
}
