package com.example.model;

import java.io.Serializable;

public class GreetingStatus implements Serializable {

    private static final long serialVersionUID = 5293363247912909366L;

    private String text;

    public GreetingStatus() {
    }

    public GreetingStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GreetingStatus [text=" + text + "]";
    }

}
