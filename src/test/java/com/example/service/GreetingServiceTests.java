package com.example.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.AbstractTest;
import com.example.model.Greeting;

@Transactional
public class GreetingServiceTests extends AbstractTest {

    @Autowired
    private GreetingService greetingService;

    @Before
    public void setup() {
        greetingService.evictCache();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindAll() {
        List<Greeting> greetings = greetingService.findAll();

        Assert.assertNotNull(greetings);
        Assert.assertEquals(3, greetings.size());
    }
    
    @Test
    public void testCreate() {
        
        Greeting greeting = new Greeting();
        greeting.setText("hello kitty");
        
        greetingService.create(greeting);

        Assert.assertNotNull(greeting.getId());
    }
}
