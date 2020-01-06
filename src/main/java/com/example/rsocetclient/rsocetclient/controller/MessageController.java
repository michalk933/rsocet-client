package com.example.rsocetclient.rsocetclient.controller;

import com.example.rsocetclient.rsocetclient.model.MessageRequest;
import com.example.rsocetclient.rsocetclient.model.MessageResponse;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RSocketRequester rSocketRequester;

    MessageController(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @GetMapping("/message/{msg}")
    Publisher<MessageResponse> message(@PathVariable("msg") String msg){
        System.out.println("CHECK == " + msg);
        return this.rSocketRequester
                .route("message")
                .data(new MessageRequest(msg))
                .retrieveMono(MessageResponse.class);
    }


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/message/stream/{msg}")
    Publisher<MessageResponse> greetStream(@PathVariable("msg") String msg){
        System.out.println("CHECK == " + msg);
        return this.rSocketRequester
                .route("greet-stream")
                .data(new MessageRequest(msg))
                .retrieveFlux(MessageResponse.class);
    }
}
