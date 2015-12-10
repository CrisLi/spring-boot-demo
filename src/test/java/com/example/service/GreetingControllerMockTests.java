package com.example.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.example.AbstractControllerTest;
import com.example.model.Greeting;
import com.example.web.api.GreetingController;

@Transactional
public class GreetingControllerMockTests extends AbstractControllerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private GreetingService greetingService;

    @InjectMocks
    private GreetingController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(controller);
    }

    @Test
    public void testGetGreetings() throws Exception {

        List<Greeting> data = Arrays.asList(new Greeting("hello kitty"));
        when(greetingService.findAll()).thenReturn(data);

        String uri = "/api/greetings";
        MvcResult result = mvc.perform(get(uri).accept(APPLICATION_JSON)).andReturn();

        verify(greetingService, times(1)).findAll();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        @SuppressWarnings("unchecked")
        List<Greeting> greetings = fromJson(content, List.class);

        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertEquals(1, greetings.size());
    }

    @Test
    public void testGetGreeting() throws Exception {

        Long id = 1L;

        Greeting data = new Greeting("hello kitty");
        data.setId(id);

        when(greetingService.findOne(id)).thenReturn(data);

        String uri = "/api/greetings/{id}";
        MvcResult result = mvc.perform(get(uri, id).accept(APPLICATION_JSON)).andReturn();

        verify(greetingService, times(1)).findOne(id);

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Greeting greeting = fromJson(content, Greeting.class);
        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertEquals(id, greeting.getId());
    }

    @Test
    public void testSendGreeting() throws Exception {

        Long id = 1L;

        Greeting data = new Greeting("hello kitty");
        data.setId(id);

        when(greetingService.findOne(id)).thenReturn(data);
        // doThrow(new RuntimeException()).when(emailService).sendAsync(data);

        String uri = "/api/greetings/{id}/send";
        MvcResult result = mvc.perform(post(uri, id).accept(APPLICATION_JSON)).andReturn();

        verify(greetingService, times(1)).findOne(id);
        verify(emailService, times(1)).sendAsync(data);

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Greeting greeting = fromJson(content, Greeting.class);
        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertEquals(id, greeting.getId());
    }
}
