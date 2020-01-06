package com.example.rsocetclient.rsocetclient.model;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageResponse {
    private String msg;

    MessageResponse(){}

    MessageResponse withGreeting(String msg){
        this.msg = msg;
        return this;
    }

    MessageResponse(String name) {
        this.withGreeting("Hello " + name + " @ " + Instant.now());
    }
}