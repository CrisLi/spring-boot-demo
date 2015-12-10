package com.example.service;

import java.util.concurrent.Future;

import com.example.model.Greeting;

public interface EmailService {

    public boolean send(Greeting greeting);
    
    public void sendAsync(Greeting greeting);
    
    public Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
