package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Greeting;

public interface GreetingRespository extends JpaRepository<Greeting, Long> {

}
