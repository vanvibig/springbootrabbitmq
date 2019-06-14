package com.example.springbootrabbitmq.runner;

import com.example.springbootrabbitmq.component.Receiver;
import com.example.springbootrabbitmq.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Senđing message...");
        rabbitTemplate.convertAndSend(
                RabbitConfig.topicExchangeName,
                "foo.bar.baz",
                "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
