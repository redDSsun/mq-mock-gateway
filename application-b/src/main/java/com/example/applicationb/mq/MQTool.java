package com.example.applicationb.mq;

import com.example.applicationb.tools.PropertiesTool;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class MQTool {

    @Autowired
    PropertiesTool propertiesTool;

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
        String mqServer = propertiesTool.mq_hostname+":"+propertiesTool.mq_port;
        System.out.println(mqServer);
        defaultMQProducer.setNamesrvAddr(mqServer);
        defaultMQProducer.start();
        setProducer(defaultMQProducer);
    }

}
