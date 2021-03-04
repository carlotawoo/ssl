package com.example.demo.service.impl;

import com.example.demo.mapper.RabbitmqMapper;
import com.example.demo.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {
    @Autowired
    private RabbitmqMapper rabbitmqMapper;
    @Override
    public void sendWork() {
        rabbitmqMapper.sendWork();
    }

    @Override
    public void sendPublish() {
        rabbitmqMapper.sendPublish();
    }

    @Override
    public void sendTopic() {
        rabbitmqMapper.sendTopic();
    }
}
