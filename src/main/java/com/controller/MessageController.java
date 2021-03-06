package com.controller;

import com.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public ResponseEntity greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Message message = new Message(counter.incrementAndGet(),
                String.format(template, name));
        return new ResponseEntity(message,HttpStatus.OK);
    }
}
