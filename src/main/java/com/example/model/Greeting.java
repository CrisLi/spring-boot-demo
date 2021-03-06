package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "GREETINGS")
public class Greeting implements Serializable {

    private static final long serialVersionUID = -4009909872380780289L;

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @Type(type="org.hibernate.type.LocalDateTimeType")
    private LocalDateTime scheduledDateTime;

    public Greeting() {
    }

    public Greeting(String text) {
        this.text = text;
        this.setScheduledDateTime(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    @Override
    public String toString() {
        return "Greeting [id=" + id + ", text=" + text + ", scheduledDateTime=" + scheduledDateTime + "]";
    }

}
