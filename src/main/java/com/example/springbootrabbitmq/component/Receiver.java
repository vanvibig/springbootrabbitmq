package com.example.springbootrabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("Received <{}>",message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
