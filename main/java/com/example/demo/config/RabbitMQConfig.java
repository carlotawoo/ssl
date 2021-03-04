package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * RabbitMQ配置类
 * @author Robert Hou
 * @date 2019年7月1日
 */
@Configuration
public class RabbitMQConfig {

    // 配置一个工作模型队列
    @Bean
    public Queue queueWork1() {
        return new Queue("queue_work");
    }

    // 发布订阅模式
    // 声明两个队列
    @Bean
    public Queue queueFanout1() {
        return new Queue("queue_fanout1");
    }
    @Bean
    public Queue queueFanout2() {
        return new Queue("queue_fanout2");
    }
    // 准备一个交换机
    @Bean
    public FanoutExchange exchangeFanout() {
        return new FanoutExchange("exchange_fanout");
    }
    // 将交换机和队列进行绑定
    @Bean
    public Binding bindingExchange1() {
        return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
    }
    @Bean
    public Binding bindingExchange2() {
        return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
    }
    // topic 模型
    @Bean
    public Queue queueTopic1() {
        return new Queue("queue_topic1");
    }
    @Bean
    public Queue queueTopic2() {
        return new Queue("queue_topic2");
    }
    @Bean
    public TopicExchange exchangeTopic() {
        return new TopicExchange("exchange_topic");
    }
    @Bean
    public Binding bindingTopic1() {
        return BindingBuilder.bind(queueTopic1()).to(exchangeTopic()).with("topic.#");
    }
    @Bean
    public Binding bindingTopic2() {
        return BindingBuilder.bind(queueTopic2()).to(exchangeTopic()).with("topic.*");
    }
}
