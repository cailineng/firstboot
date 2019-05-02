package com.lineng.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Component
@RabbitListener(queues = "searchSystemQueue")*/
public class ReceiverTopic {

    @RabbitHandler
    public void process(String context) {
        System.out.println("search receive : " +context);
    }

}
