package com.example.web.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import com.example.AbstractControllerTest;
import com.example.model.Greeting;
import com.example.service.GreetingService;

@Transactional
public class GreetingControllerTests extends AbstractControllerTest {

    @Autowired
    private GreetingService greetingService;

    @Before
    public void setUp() {
        super.setUp();
        greetingService.evictCache();
    }

    @Test
    public void testGetGreetings() throws Exception {

        String uri = "/api/greetings";

        MvcResult result = mvc.perform(get(uri).accept(APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        @SuppressWarnings("unchecked")
        List<Greeting> greetings = fromJson(content, List.class);

        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertEquals(3, greetings.size());
    }

    @Test
    public void testGetGreeting() throws Exception {

        String uri = "/api/greetings/{id}";
        Long id = 1L;

        MvcResult result = mvc.perform(get(uri, id).accept(APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Greeting greeting = fromJson(content, Greeting.class);

        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertEquals(id, greeting.getId());
    }

    @Test
    @WithMockUser
    public void testCreateGreeting() throws Exception {

        String uri = "/api/greetings";
        Greeting greeting = new Greeting();
        greeting.setText("hello kitty");
        greeting.setScheduledDateTime(LocalDateTime.now());

        MvcResult result = mvc
                .perform(post(uri) //
                        .contentType(APPLICATION_JSON) //
                        .accept(APPLICATION_JSON)//
                        .content(toJson(greeting))) //
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Greeting newGreeting = fromJson(content, Greeting.class);

        Assert.assertEquals(HttpStatus.CREATED.value(), status);
        Assert.assertNotNull(newGreeting.getId());
    }
}
