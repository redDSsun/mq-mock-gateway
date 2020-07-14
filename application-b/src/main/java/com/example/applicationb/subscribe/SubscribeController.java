package com.example.applicationb.subscribe;

import com.example.applicationb.mq.MQTool;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {

    @Autowired
    MQTool mqTool;

    @RequestMapping("/subscribe/{subscribe}")
    public void sendSubscribe(@PathVariable("subscribe") String subscribe) throws Exception {
        String[] subscribeList = subscribe.split(",");

        DefaultMQProducer defaultMQProducer = mqTool.getProducer();

        for (String sub: subscribeList) {
            Message message = new Message("subscribe",("application-b:"+sub).getBytes());
            try {
                SendResult sendResult = defaultMQProducer.send(message);
                System.out.println("subscribe "+subscribe+" successful");
            }catch (Exception e){
                System.out.println("subscribe "+subscribe+" failed");
            }

        }

    }
}
