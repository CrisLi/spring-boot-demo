package com.example.web.api;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.CurrentUser;
import com.example.model.Greeting;
import com.example.service.EmailService;
import com.example.service.GreetingService;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Greeting>> greetings() {
        List<Greeting> greetings = greetingService.findAll();
        return new ResponseEntity<>(greetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Greeting> greeting(@PathVariable Long id) {
        return new ResponseEntity<>(greetingService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Greeting> create(@RequestBody Greeting greeting, @CurrentUser User user) {
        logger.info("{} is creating a new Greeting.", user.getUsername());
        return new ResponseEntity<>(greetingService.create(greeting), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Greeting> update(@PathVariable("id") Long id, @RequestBody Greeting greeting) {
        return new ResponseEntity<>(greetingService.update(id, greeting), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/send", method = RequestMethod.POST)
    public ResponseEntity<Greeting> send(@PathVariable("id") Long id,
            @RequestParam(value = "wait", defaultValue = "false") boolean wait) {

        Greeting greeting = greetingService.findOne(id);

        if (wait) {
            Future<Boolean> result = emailService.sendAsyncWithResult(greeting);
            try {
                logger.info("sent email result {}", result.get());
            } catch (InterruptedException | ExecutionException e) {
                logger.info("get sent email result error", e);
            }
        } else {
            emailService.sendAsync(greeting);
        }

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
