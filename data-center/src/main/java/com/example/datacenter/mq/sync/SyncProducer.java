package com.example.datacenter.mq.sync;

import com.example.datacenter.service.data.Data;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class SyncProducer {
    MQProducer mqProducer;

    public MQProducer getMqProducer() {
        if (mqProducer == null){
            startProducer();
        }
        return mqProducer;
    }

    public void setMqProducer(MQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }



    @PostConstruct
    public void startProducer(){
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("test");
        defaultMQProducer.setNamesrvAddr("192.168.2.106:9876");
        try {
            defaultMQProducer.start();
            setMqProducer(defaultMQProducer);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(Data data, String tag) {
        System.out.println("send message"+data+":"+tag);
        Message message = new Message("sync", tag, data.toString().getBytes());
        try {
            SendResult sendResult = getMqProducer().send(message);
            System.out.println(sendResult);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
