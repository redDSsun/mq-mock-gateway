package com.example.applicationb.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class MQTool {
    private DefaultMQProducer producer;

    public DefaultMQProducer getProducer() throws MQClientException {
        if (producer == null){
            startMQ();
        }
        return producer;
    }

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    @PostConstruct
    public void startMQ() throws MQClientException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("subscribe");
        defaultMQProducer.setNamesrvAddr("192.168.2.106:9876");
        defaultMQProducer.start();
        setProducer(defaultMQProducer);
    }

}
