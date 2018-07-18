package com.lineng.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "frontSystemQueue")
public class ReceiverTopic2 {

    @RabbitHandler
    public void process(String context) {
        System.out.println("front Receive: " +context);
    }

}
