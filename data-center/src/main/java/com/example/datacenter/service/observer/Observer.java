package com.example.datacenter.service.observer;

import com.example.datacenter.mq.sync.SyncProducer;
import com.example.datacenter.service.data.Data;
import com.example.datacenter.service.data.DataProvider;
import com.example.datacenter.service.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class Observer {
    @Autowired
    SyncProducer syncProducer;

    @Autowired
    DataService dataService;

    HashMap<String, Set<String>> consumer_suscribe = new HashMap<>();
    public HashMap<String, Set<String>> getConsumer_suscribe() {
        return consumer_suscribe;
    }

    public void setConsumer_suscribe(HashMap<String, Set<String>> consumer_suscribe) {
        this.consumer_suscribe = consumer_suscribe;
        System.out.println(consumer_suscribe);
    }


    public void handle(Data data, String type){
        if (consumer_suscribe.containsKey(type)){
            for (String consumer : consumer_suscribe.get(type)){
                syncProducer.sendMessage(data, consumer);
            }
        }

    }

    @PostConstruct
    public void registObserver(){
        dataService.setObserver(this);
    }
}
