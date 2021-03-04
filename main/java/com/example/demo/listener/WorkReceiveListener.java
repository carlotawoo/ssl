package com.example.demo.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


// 2个消费者
@Component
public class WorkReceiveListener {
    @RabbitListener(queues = "queue_work")
    public void receiveMessage(String msg, Channel channel, Message message) {
        // 只包含发送的消息
        System.out.println("1接收到消息：" + msg);
        // channel 通道信息
        // message 附加的参数信息
    }

    @RabbitListener(queues = "queue_work")
    public void receiveMessage2(Object obj, Channel channel, Message message) {
        // 包含所有的信息
        System.out.println("2接收到消息：" + obj);
    }

    @RabbitListener(queues = "queue_fanout1")
    public void receiveMsg1(String msg) {
        System.out.println("队列1接收到消息：" + msg);
    }

    @RabbitListener(queues = "queue_fanout2")
    public void receiveMsg2(String msg) {
        System.out.println("队列2接收到消息：" + msg);
    }

    @RabbitListener(queues = "queue_topic1")
    public void receiveTop1(String msg) {
        System.out.println("消费者1接收到：" + msg);
    }

    @RabbitListener(queues = "queue_topic2")
    public void receiveTop2(String msg) {
        System.out.println("消费者2接收到：" + msg);
    }
}