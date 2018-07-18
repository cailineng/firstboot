package com.lineng.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    final static String topicShopUpdate = "topic.shop.update";
    final static String topicShopInsert = "topic.shop.insert";
    final static String topicShopDelete = "topic.shop.delete";
    final static String topicShopAll = "topic.shop.#";
    @Bean
    public Queue queueSearchSystem() {
        return new Queue("searchSystemQueue");
    }

    @Bean
    public Queue queueFrontSystem() {
        return new Queue("frontSystemQueue");
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("linengDemoExchange");
    }

    @Bean
    Binding bindingExchangeSearchSystem(Queue queueSearchSystem, TopicExchange exchange) {
        return BindingBuilder.bind(queueSearchSystem).to(exchange).with(topicShopUpdate);
    }

    @Bean
    Binding bindingExchangeSearchSystem2(Queue queueSearchSystem, TopicExchange exchange) {
        return BindingBuilder.bind(queueSearchSystem).to(exchange).with(topicShopInsert);
    }

    @Bean
    Binding bindingExchangeFrontSystem(Queue queueFrontSystem, TopicExchange exchange) {
        return BindingBuilder.bind(queueFrontSystem).to(exchange).with(topicShopAll);
    }
}
