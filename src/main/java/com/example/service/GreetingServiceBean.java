package com.example.service;

import static com.example.config.CacheConfig.GREETINGS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.DataInvalidException;
import com.example.exception.ResourceNotFoundException;
import com.example.jms.MessageSender;
import com.example.model.Greeting;
import com.example.repository.GreetingRespository;

@Service
@Transactional
@CacheConfig(cacheNames = GREETINGS)
public class GreetingServiceBean implements GreetingService {

    @Autowired
    private GreetingRespository greetingRespository;

    @Autowired
    private MessageSender<Greeting> messageSender;

    @CachePut(key = "#result.id")
    @Override
    public Greeting create(Greeting greeting) {

        greetingRespository.save(greeting);

        if (greeting.getId() == 5) {
            throw new DataInvalidException(Greeting.class);
        }

        messageSender.send("greetings", "create-greeting", greeting);

        return greeting;
    }

    @CachePut(key = "#id")
    @Override
    public Greeting update(Long id, Greeting greeting) {
        Greeting oldGreeting = this.findOne(id);
        oldGreeting.setText(greeting.getText());
        return greetingRespository.save(oldGreeting);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    @Override
    public Greeting findOne(Long id) {
        Greeting greeting = greetingRespository.findOne(id);
        if (greeting == null) {
            throw new ResourceNotFoundException(Greeting.class);
        }
        return greetingRespository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Greeting> findAll() {
        return greetingRespository.findAll();
    }

    @CacheEvict(allEntries = true)
    @Override
    public void evictCache() {
    }

}
