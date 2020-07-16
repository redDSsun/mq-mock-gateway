package com.example.datacenter.mq.subscribe;

import com.example.datacenter.service.observer.Observer;
import com.example.datacenter.tools.PropertiesTool;
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
public class SubscribeConsumer{

    @Autowired
    Observer observer;

    @Autowired
    PropertiesTool propertiesTool;


    @PostConstruct
    public void startConsumer() throws Exception {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("subscribe");
        String mqServer = propertiesTool.mq_hostname+":"+propertiesTool.mq_port;
        System.out.println(mqServer);
        defaultMQPushConsumer.setNamesrvAddr(mqServer);
        defaultMQPushConsumer.subscribe("subscribe", "*");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt message: list
                     ) {
                    String messageBody = new String(message.getBody());
                    String consumer = messageBody.split(":")[0];
                    String type = messageBody.split(":")[1];
                    HashMap<String, Set<String>> consumer_suscribe = observer.getConsumer_suscribe();
                    if (!consumer_suscribe.containsKey(type)){
                        Set<String> set = new HashSet<>();
                        set.add(consumer);
                        consumer_suscribe.put(type, set);
                        observer.setConsumer_suscribe(consumer_suscribe);
                    }else{
                        consumer_suscribe.get(type).add(consumer);
                        observer.setConsumer_suscribe(consumer_suscribe);
                    }

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        defaultMQPushConsumer.start();
    }


}
