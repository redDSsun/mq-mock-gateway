package com.example.applicationa.sync;

import com.example.applicationa.tools.PropertiesTool;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SyncConsumer {

    @Autowired
    PropertiesTool propertiesTool;

    @PostConstruct
    public void startConsumer() throws Exception {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("application-a");
        String mqServer = propertiesTool.mq_hostname+":"+propertiesTool.mq_port;
        System.out.println(mqServer);
        defaultMQPushConsumer.setNamesrvAddr(mqServer);
        defaultMQPushConsumer.subscribe("sync", "application-a");
        System.out.println("====");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt message: list
                ) {
                    String messageBody = new String(message.getBody());
                    System.out.println(messageBody);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        defaultMQPushConsumer.start();
    }
}
