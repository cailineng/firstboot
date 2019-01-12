package com.lineng.rabbit;


import com.lineng.model.Cat;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

@Component
public class Receiver {

    @RabbitListener(queues = "hello")
    public void processMessage(Message message, Channel channel) throws Exception {
        byte[] content = message.getBody();
      //  Cat cat = (Cat) ByteUtil.getObjectFromBytes(content);
       // System.out.println("收到"+cat.getCatName());
        //确认消息成功消费
       channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
