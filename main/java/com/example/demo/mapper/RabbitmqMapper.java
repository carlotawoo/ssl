package com.example.demo.mapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqMapper {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("queue_work", "测试work模型: " + i);
        }
    }

    // 向发布订阅模式里面发送消息
    public void sendPublish() {
        for (int i = 0; i < 5; i++) {
            // rabbitTemplate.convertSendAndReceive("exchange_fanout", "", "测试发布订阅模型：" + i);
            rabbitTemplate.convertAndSend("exchange_fanout", "", "测试发布订阅模型：" + i);
        }
    }
    // 向topic模型发送数据
    public void sendTopic() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                rabbitTemplate.convertSendAndReceive("exchange_topic", "topic.km.topic", "测试发布订阅模型：" + i);
            } else {
                rabbitTemplate.convertSendAndReceive("exchange_topic", "topic.km", "测试发布订阅模型：" + i);

            }
        }
    }
}