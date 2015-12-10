package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.CacheConfig;
import com.example.exception.DataInvalidException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Greeting;
import com.example.repository.GreetingRespository;

@Service
@Transactional
public class GreetingServiceBean implements GreetingService {

    @Autowired
    private GreetingRespository greetingRespository;

    @CachePut(value = CacheConfig.GREETINGS, key = "#result.id")
    @Override
    public Greeting create(Greeting greeting) {

        greetingRespository.save(greeting);

        if (greeting.getId() == 5) {
            throw new DataInvalidException(Greeting.class);
        }

        return greeting;
    }

    @CachePut(value = CacheConfig.GREETINGS, key = "#id")
    @Override
    public Greeting update(Long id, Greeting greeting) {
        Greeting oldGreeting = this.findOne(id);
        oldGreeting.setText(greeting.getText());
        return greetingRespository.save(oldGreeting);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = CacheConfig.GREETINGS, key = "#id")
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

    @CacheEvict(value = CacheConfig.GREETINGS, allEntries = true)
    @Override
    public void evictCache() {
    }

}
