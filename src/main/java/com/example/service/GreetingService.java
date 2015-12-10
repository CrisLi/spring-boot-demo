package com.example.service;

import java.util.List;

import com.example.model.Greeting;

public interface GreetingService {

    public Greeting create(Greeting greeting);

    public Greeting update(Long id, Greeting greeting);

    public Greeting findOne(Long id);

    public List<Greeting> findAll();
    
    public void evictCache();
}
